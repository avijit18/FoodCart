package com.FoodCart.Services.IMPL;

import com.FoodCart.Entities.*;
import com.FoodCart.Exceptions.CartException;
import com.FoodCart.Exceptions.OrderException;
import com.FoodCart.Exceptions.RestaurantException;
import com.FoodCart.Exceptions.UserException;
import com.FoodCart.Repositories.*;
import com.FoodCart.Requests.CreateOrderRequest;
import com.FoodCart.Response.PaymentResponseDTO;
import com.FoodCart.Services.Interfaces.CartService;
import com.FoodCart.Services.Interfaces.NotificationService;
import com.FoodCart.Services.Interfaces.OrderService;
import com.FoodCart.Services.Interfaces.PaymentService;
import com.stripe.exception.StripeException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImplementation implements OrderService {

    private final AddressRepository addressRepository;
    private final CartService cartService;
    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;
    private final PaymentService paymentService;
    private final NotificationService notificationService;


    @Override
    public PaymentResponseDTO createOrder(CreateOrderRequest order, UserEntity user)
            throws UserException, RestaurantException, CartException, StripeException {
//        Address shippAddress = order.getDeliveryAddress();
//        Address savedAddress = addressRepository.save(shippAddress);
//
//        if (!user.getAddresses().contains(savedAddress)) {
//            user.getAddresses().add(savedAddress);
//        }
//        System.out.println("user addresses --------------  " + user.getAddresses());
//
//        UserEntity savedUser = userRepository.save(user);
//
//        Optional<Restaurant> restaurant = restaurantRepository.findById(order.getRestaurantId());
//        if (restaurant.isEmpty()) {
//            throw new RestaurantException("Restaurant not found with id " + order.getRestaurantId());
//        }
//        Order createdOrder = new Order();
//
//        createdOrder.setCustomer(savedUser);
//       // createdOrder.setCustomer(user);
//        savedUser.getOrders().add(createdOrder);
//        createdOrder.setDeliveryAddress(savedAddress);
//        createdOrder.setCreatedAt(new Date());
//        createdOrder.setOrderStatus("PENDING");
//        createdOrder.setRestaurant(restaurant.get());
//
//        Cart cart = cartService.findCartByUserId(user.getId());
//
//        List<OrderItem> orderItems = new ArrayList<>();
//
//        for (CartItem cartItem : cart.getItems()) {
//            OrderItem orderItem = new OrderItem();
//            orderItem.setFood(cartItem.getFood());
//            orderItem.setIngredients(cartItem.getIngredients());
//            orderItem.setQuantity(cartItem.getQuantity());
//            orderItem.setTotalPrice(cartItem.getFood().getPrice() * cartItem.getQuantity());
//
//            OrderItem savedOrderItem = orderItemRepository.save(orderItem);
//            orderItems.add(savedOrderItem);
//        }
//
//        Long totalPrice = cartService.calculateCartTotals(cart);
//
//        createdOrder.setTotalAmount(totalPrice);
//        createdOrder.setRestaurant(restaurant.get());
//
//        createdOrder.setItems(orderItems);
//        Order savedOrder = orderRepository.save(createdOrder);
//
//        restaurant.get().getOrders().add(savedOrder);
//
//        restaurantRepository.save(restaurant.get());
//
//        PaymentResponseDTO res = paymentService.generatePaymentLink(savedOrder);
//        return res;

        // Check if the user exists
        if (user.getId() == null || !userRepository.existsById(user.getId())) {
            throw new UserException("User not found with id " + user.getId());
        }

        // Save the shipping address
        Address shippAddress = order.getDeliveryAddress();
        Address savedAddress = addressRepository.save(shippAddress);

        // Update the user's addresses
        if (!user.getAddresses().contains(savedAddress)) {
            user.getAddresses().add(savedAddress);
        }
        userRepository.save(user);

        // Find the restaurant
        Optional<Restaurant> restaurant = restaurantRepository.findById(order.getRestaurantId());
        if (restaurant.isEmpty()) {
            throw new RestaurantException("Restaurant not found with id " + order.getRestaurantId());
        }

        // Create and save the order
        Order createdOrder = new Order();
        createdOrder.setCustomer(user);
        createdOrder.setDeliveryAddress(savedAddress);
        createdOrder.setCreatedAt(new Date());
        createdOrder.setOrderStatus("PENDING");
        createdOrder.setRestaurant(restaurant.get());

        // Process the cart items
        Cart cart = cartService.findCartByUserId(user.getId());
        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItem cartItem : cart.getItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setFood(cartItem.getFood());
            orderItem.setIngredients(cartItem.getIngredients());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setTotalPrice(cartItem.getFood().getPrice() * cartItem.getQuantity());
            OrderItem savedOrderItem = orderItemRepository.save(orderItem);
            orderItems.add(savedOrderItem);
        }
        Long totalPrice = cartService.calculateCartTotals(cart);
        createdOrder.setTotalAmount(totalPrice);
        createdOrder.setItems(orderItems);

        // Save the order
        Order savedOrder = orderRepository.save(createdOrder);

        // Update the restaurant with the new order
        Restaurant rest = restaurant.get();
        rest.getOrders().add(savedOrder);
        restaurantRepository.save(rest);

        // Generate payment response
        PaymentResponseDTO res = paymentService.generatePaymentLink(savedOrder);
        return res;
    }

    @Override
    public Order updateOrder(Long orderId, String orderStatus) throws OrderException {
        Order order = findOrderById(orderId);
        System.out.println("--------- " + orderStatus);
        if (orderStatus.equals("OUT_FOR_DELIVERY") || orderStatus.equals("DELIVERED")
                || orderStatus.equals("COMPLETED") || orderStatus.equals("PENDING")) {
            order.setOrderStatus(orderStatus);
            Notification notification = notificationService.sendOrderStatusNotification(order);
            return orderRepository.save(order);
        } else throw new OrderException("Please Select A Valid Order Status");
    }

    @Override
    public void cancelOrder(Long orderId) throws OrderException {
        Order order = findOrderById(orderId);
        if (order == null) {
            throw new OrderException("Order not found with the id " + orderId);
        }

        orderRepository.deleteById(orderId);
    }

    public Order findOrderById(Long orderId) throws OrderException {
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isPresent()) return order.get();

        throw new OrderException("Order not found with the id " + orderId);
    }

    @Override
    public List<Order> getUserOrders(Long userId) throws OrderException {
        List<Order> orders = orderRepository.findAllUserOrders(userId);
        return orders;
    }

    @Override
    public List<Order> getOrdersOfRestaurant(Long restaurantId, String orderStatus)
            throws OrderException, RestaurantException {
        List<Order> orders = orderRepository.findOrdersByRestaurantId(restaurantId);

        if (orderStatus != null) {
            orders = orders.stream()
                    .filter(order -> order.getOrderStatus().equals(orderStatus))
                    .collect(Collectors.toList());
        }
        return orders;
    }
}
