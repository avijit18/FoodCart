package com.FoodCart.Services.Interfaces;

import com.FoodCart.Entities.Category;
import com.FoodCart.Entities.Food;
import com.FoodCart.Entities.Restaurant;
import com.FoodCart.Exceptions.FoodException;
import com.FoodCart.Exceptions.RestaurantException;
import com.FoodCart.Requests.CreateFoodRequest;

import java.util.List;

public interface FoodService {
    public Food createFood(CreateFoodRequest req, Category category,
                           Restaurant restaurant) throws FoodException, RestaurantException;

    void deleteFood(Long foodId) throws FoodException;

    public List<Food> getRestaurantsFood(Long restaurantId,
                                         boolean isVegetarian, boolean isNonveg, boolean isSeasonal, String foodCategory) throws FoodException;

    public List<Food> searchFood(String keyword);

    public Food findFoodById(Long foodId) throws FoodException;

    public Food updateAvailibilityStatus(Long foodId) throws FoodException;
}
