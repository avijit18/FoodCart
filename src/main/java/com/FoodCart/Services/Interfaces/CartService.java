package com.FoodCart.Services.Interfaces;

import com.FoodCart.Entities.Cart;
import com.FoodCart.Entities.CartItem;
import com.FoodCart.Exceptions.CartException;
import com.FoodCart.Exceptions.CartItemException;
import com.FoodCart.Exceptions.FoodException;
import com.FoodCart.Exceptions.UserException;
import com.FoodCart.Requests.AddCartItemRequest;

public interface CartService {
    public CartItem addItemToCart(AddCartItemRequest req, String jwt) throws UserException, FoodException, CartException, CartItemException;

    public CartItem updateCartItemQuantity(Long cartItemId, int quantity) throws CartItemException;

    public Cart removeItemFromCart(Long cartItemId, String jwt) throws UserException, CartException, CartItemException;

    public Long calculateCartTotals(Cart cart) throws UserException;

    public Cart findCartById(Long id) throws CartException;

    public Cart findCartByUserId(Long userId) throws CartException, UserException;

    public Cart clearCart(Long userId) throws CartException, UserException;
}
