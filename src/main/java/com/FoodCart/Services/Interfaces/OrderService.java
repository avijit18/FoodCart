package com.FoodCart.Services.Interfaces;

import com.FoodCart.Entities.Order;
import com.FoodCart.Response.PaymentResponseDTO;
import com.FoodCart.Entities.UserEntity;
import com.FoodCart.Exceptions.CartException;
import com.FoodCart.Exceptions.OrderException;
import com.FoodCart.Exceptions.RestaurantException;
import com.FoodCart.Exceptions.UserException;
import com.FoodCart.Requests.CreateOrderRequest;
import com.stripe.exception.StripeException;

import java.util.List;

public interface OrderService {
    public PaymentResponseDTO createOrder(CreateOrderRequest order, UserEntity user) throws UserException, RestaurantException, CartException, StripeException;

    public Order updateOrder(Long orderId, String orderStatus) throws OrderException;

    public void cancelOrder(Long orderId) throws OrderException;

    public List<Order> getUserOrders(Long userId) throws OrderException;

    public List<Order> getOrdersOfRestaurant(Long restaurantId, String orderStatus) throws OrderException, RestaurantException;
}
