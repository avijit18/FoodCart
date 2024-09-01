package com.FoodCart.Requests;

import lombok.Data;

import java.util.List;

@Data
public class AddCartItemRequest {
    private Long menuItemId;
    private int quantity;
    private List<String> ingredients;
}
