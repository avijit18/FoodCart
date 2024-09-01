package com.FoodCart.Requests;

import com.FoodCart.Entities.Address;
import lombok.Data;

@Data
public class CreateOrderRequest {
    private Long restaurantId;

    private Address deliveryAddress;
}
