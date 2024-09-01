package com.FoodCart.Exceptions;

public class CartException extends RuntimeException {
    public CartException() {

    }
    public CartException(String message) {
        super(message);
    }
}
