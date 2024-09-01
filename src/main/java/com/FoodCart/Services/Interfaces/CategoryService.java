package com.FoodCart.Services.Interfaces;

import com.FoodCart.Entities.Category;
import com.FoodCart.Exceptions.RestaurantException;

import java.util.List;

public interface CategoryService {
    public Category createCategory (String name,Long userId) throws RestaurantException;
    public List<Category> findCategoryByRestaurantId(Long restaurantId) throws RestaurantException;
    public Category findCategoryById(Long id) throws RestaurantException;
}
