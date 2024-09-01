package com.FoodCart.Exceptions;

public class CartItemException extends RuntimeException {
    public CartItemException() {

    }
    public CartItemException(String message) {
        super(message);
    }
}
