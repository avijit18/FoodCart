package com.FoodCart.Services.Interfaces;

import com.FoodCart.Entities.IngredientCategory;
import com.FoodCart.Entities.IngredientsItem;
import com.FoodCart.Exceptions.RestaurantException;

import java.util.List;

public interface IngredientsService {
    public IngredientCategory createIngredientsCategory(
            String name,Long restaurantId) throws RestaurantException;

    public IngredientCategory findIngredientsCategoryById(Long id) throws Exception;

    public List<IngredientCategory> findIngredientsCategoryByRestaurantId(Long id) throws Exception;

    public List<IngredientsItem> findRestaurantsIngredients(
            Long restaurantId);


    public IngredientsItem createIngredientsItem(Long restaurantId,
                                                 String ingredientName,Long ingredientCategoryId) throws Exception;

    public IngredientsItem updateStoke(Long id) throws Exception;
}
