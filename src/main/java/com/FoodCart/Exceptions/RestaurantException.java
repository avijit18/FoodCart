package com.FoodCart.Exceptions;

public class RestaurantException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public RestaurantException(String message) {
        super(message);
    }

    public RestaurantException() {

    }
}
