package com.FoodCart.Controllers;

import com.FoodCart.Entities.Order;
import com.FoodCart.Response.PaymentResponseDTO;
import com.FoodCart.Entities.UserEntity;
import com.FoodCart.Exceptions.CartException;
import com.FoodCart.Exceptions.OrderException;
import com.FoodCart.Exceptions.RestaurantException;
import com.FoodCart.Exceptions.UserException;
import com.FoodCart.Requests.CreateOrderRequest;
import com.FoodCart.Services.Interfaces.OrderService;
import com.FoodCart.Services.Interfaces.UserService;
import com.stripe.exception.StripeException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;

    @PostMapping("/order")
    public ResponseEntity<PaymentResponseDTO> createOrder(@RequestBody CreateOrderRequest order,
                                                          @RequestHeader("Authorization") String jwt)
            throws UserException, RestaurantException,
            CartException,
            StripeException,
            OrderException {
        UserEntity user = userService.findUserProfileByJwt(jwt);
        if (user == null || user.getId() == null) {
            throw new UserException("User not found or not saved correctly.");
        }
        System.out.println("req user " + user.getEmail());
        if (order != null) {
            PaymentResponseDTO res = orderService.createOrder(order, user);
            return ResponseEntity.ok(res);

        } else throw new OrderException("Please provide valid request body");
    }

    @GetMapping("/order/user")
    public ResponseEntity<List<Order>> getAllUserOrders(@RequestHeader("Authorization") String jwt)
            throws OrderException, UserException {

        UserEntity user = userService.findUserProfileByJwt(jwt);

        if (user.getId() != null) {
            List<Order> userOrders = orderService.getUserOrders(user.getId());
            return ResponseEntity.ok(userOrders);
        } else {
            return new ResponseEntity<List<Order>>(HttpStatus.BAD_REQUEST);
        }
    }
}
