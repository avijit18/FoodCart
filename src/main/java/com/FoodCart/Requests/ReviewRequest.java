package com.FoodCart.Requests;

import lombok.Data;

@Data
public class ReviewRequest {
    private Long restaurantId;

    private double rating;

    private String reviewText;
}
