package com.FoodCart.Requests;

import lombok.Data;

@Data
public class CreateIngredientRequest {
    private Long restaurantId;
    private String name;
    private Long ingredientCategoryId;
}
