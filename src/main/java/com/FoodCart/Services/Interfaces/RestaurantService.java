package com.FoodCart.Services.Interfaces;

import com.FoodCart.DTOs.RestaurantDTO;
import com.FoodCart.Entities.Restaurant;
import com.FoodCart.Entities.UserEntity;
import com.FoodCart.Exceptions.RestaurantException;
import com.FoodCart.Requests.CreateRestaurantRequest;

import java.util.List;

public interface RestaurantService {
    public Restaurant createRestaurant(CreateRestaurantRequest req, UserEntity user);

    public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updatedRestaurant)
            throws RestaurantException;

    public void deleteRestaurant(Long restaurantId) throws RestaurantException;

    public List<Restaurant> getAllRestaurant();

    public List<Restaurant>searchRestaurant(String keyword);

    public Restaurant findRestaurantById(Long id) throws RestaurantException;

    public Restaurant getRestaurantsByUserId(Long userId) throws RestaurantException;

    public RestaurantDTO addToFavorites(Long restaurantId, UserEntity user) throws RestaurantException;

    public Restaurant updateRestaurantStatus(Long id)throws RestaurantException;
}
