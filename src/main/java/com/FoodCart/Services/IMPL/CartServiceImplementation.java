package com.FoodCart.Services.IMPL;

import com.FoodCart.Entities.Cart;
import com.FoodCart.Entities.CartItem;
import com.FoodCart.Entities.Food;
import com.FoodCart.Entities.UserEntity;
import com.FoodCart.Exceptions.CartException;
import com.FoodCart.Exceptions.CartItemException;
import com.FoodCart.Exceptions.FoodException;
import com.FoodCart.Exceptions.UserException;
import com.FoodCart.Repositories.CartItemRepository;
import com.FoodCart.Repositories.CartRepository;
import com.FoodCart.Repositories.FoodRepository;
import com.FoodCart.Requests.AddCartItemRequest;
import com.FoodCart.Services.Interfaces.CartService;
import com.FoodCart.Services.Interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImplementation implements CartService {

    private final CartRepository cartRepository;
    private final UserService userService;
    private final CartItemRepository CartItemRepository;
    private final FoodRepository menuItemRepository;


    @Override
    public CartItem addItemToCart(AddCartItemRequest req, String jwt) throws UserException,
            FoodException, CartException, CartItemException {
        UserEntity user = userService.findUserProfileByJwt(jwt);

        Optional<Food> menuItem = menuItemRepository.findById(req.getMenuItemId());
        if (menuItem.isEmpty()) {
            throw new FoodException("Menu Item not exist with id " + req.getMenuItemId());
        }

        Cart cart = findCartByUserId(user.getId());

        for (CartItem cartItem : cart.getItems()) {
            if (cartItem.getFood().equals(menuItem.get())) {

                int newQuantity = cartItem.getQuantity() + req.getQuantity();
                return updateCartItemQuantity(cartItem.getId(), newQuantity);
            }
        }

        CartItem newCartItem = new CartItem();
        newCartItem.setFood(menuItem.get());
        newCartItem.setQuantity(req.getQuantity());
        newCartItem.setCart(cart);
        newCartItem.setIngredients(req.getIngredients());
        newCartItem.setTotalPrice(req.getQuantity() * menuItem.get().getPrice());

        CartItem savedItem = CartItemRepository.save(newCartItem);
        cart.getItems().add(savedItem);
        cartRepository.save(cart);

        return savedItem;
    }

    @Override
    public CartItem updateCartItemQuantity(Long cartItemId, int quantity) throws CartItemException {
        Optional<CartItem> cartItem=CartItemRepository.findById(cartItemId);
        if(cartItem.isEmpty()) {
            throw new CartItemException("cart item not exist with id "+cartItemId);
        }
        cartItem.get().setQuantity(quantity);
        cartItem.get().setTotalPrice((cartItem.get().getFood().getPrice()*quantity));
        return CartItemRepository.save(cartItem.get());
    }

    @Override
    public Cart removeItemFromCart(Long cartItemId, String jwt) throws UserException, CartException, CartItemException {
        UserEntity user = userService.findUserProfileByJwt(jwt);

        Cart cart = findCartByUserId(user.getId());

        Optional<CartItem> cartItem=CartItemRepository.findById(cartItemId);

        if(cartItem.isEmpty()) {
            throw new CartItemException("cart item not exist with id "+cartItemId);
        }

        cart.getItems().remove(cartItem.get());
        return cartRepository.save(cart);
    }

    @Override
    public Long calculateCartTotals(Cart cart) throws UserException {
        Long total = 0L;
        for (CartItem cartItem : cart.getItems()) {
            total += cartItem.getFood().getPrice() * cartItem.getQuantity();
        }
        return total;
    }

    @Override
    public Cart findCartById(Long id) throws CartException {
        Optional<Cart> cart = cartRepository.findById(id);
        if(cart.isPresent()) {
            return cart.get();
        }
        throw new CartException("Cart not found with the id "+id);
    }

    @Override
    public Cart findCartByUserId(Long userId) throws CartException, UserException {
        Optional<Cart> opt=cartRepository.findByCustomer_Id(userId);
        if(opt.isPresent()) {
            return opt.get();
        }
        throw new CartException("cart not found");
    }

    @Override
    public Cart clearCart(Long userId) throws CartException, UserException {
        Cart cart=findCartByUserId(userId);
        cart.getItems().clear();
        return cartRepository.save(cart);
    }
}
