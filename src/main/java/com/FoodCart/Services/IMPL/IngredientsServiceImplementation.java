package com.FoodCart.Services.IMPL;

import com.FoodCart.Entities.IngredientCategory;
import com.FoodCart.Entities.IngredientsItem;
import com.FoodCart.Entities.Restaurant;
import com.FoodCart.Exceptions.RestaurantException;
import com.FoodCart.Repositories.IngredientsCategoryRepository;
import com.FoodCart.Repositories.IngredientsItemRepository;
import com.FoodCart.Services.Interfaces.IngredientsService;
import com.FoodCart.Services.Interfaces.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IngredientsServiceImplementation implements IngredientsService {

    private final IngredientsCategoryRepository ingredientsCategoryRepo;
    private final IngredientsItemRepository ingredientsItemRepository;
    private final RestaurantService restaurantService;


    @Override
    public IngredientCategory createIngredientsCategory(String name, Long restaurantId)
            throws RestaurantException {
        IngredientCategory isExist = ingredientsCategoryRepo
                .findByRestaurantIdAndNameIgnoreCase(restaurantId, name);

        if (isExist != null) {
            return isExist;
        }
        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);

        IngredientCategory ingredientCategory = new IngredientCategory();
        ingredientCategory.setRestaurant(restaurant);
        ingredientCategory.setName(name);

        IngredientCategory createdCategory = ingredientsCategoryRepo.save(ingredientCategory);

        return createdCategory;
    }

    @Override
    public IngredientCategory findIngredientsCategoryById(Long id) throws Exception {
        Optional<IngredientCategory> opt = ingredientsCategoryRepo.findById(id);
        if (opt.isEmpty()) {
            throw new Exception("ingredient category not found");
        }
        return opt.get();
    }

    @Override
    public List<IngredientCategory> findIngredientsCategoryByRestaurantId(Long id) throws Exception {
        return ingredientsCategoryRepo.findByRestaurantId(id);
    }

    @Override
    public List<IngredientsItem> findRestaurantsIngredients(Long restaurantId) {
        return ingredientsItemRepository.findByRestaurantId(restaurantId);
    }

    @Override
    public IngredientsItem createIngredientsItem(Long restaurantId, String ingredientName, Long ingredientCategoryId)
            throws Exception {
        IngredientCategory category = findIngredientsCategoryById(ingredientCategoryId);

        IngredientsItem isExist = ingredientsItemRepository.
                findByRestaurantIdAndNameIgnoreCase(restaurantId, ingredientName, category.getName());
        if (isExist != null) {
            System.out.println("is exists-------- item");
            return isExist;
        }
        Restaurant restaurant = restaurantService.findRestaurantById(
                restaurantId);
        IngredientsItem item = new IngredientsItem();
        item.setName(ingredientName);
        item.setRestaurant(restaurant);
        item.setCategory(category);

        IngredientsItem savedIngredients = ingredientsItemRepository.save(item);
        category.getIngredients().add(savedIngredients);

        return savedIngredients;
    }

    @Override
    public IngredientsItem updateStoke(Long id) throws Exception {
        Optional<IngredientsItem> item = ingredientsItemRepository.findById(id);
        if (item.isEmpty()) {
            throw new Exception("ingredient not found with id " + item);
        }
        IngredientsItem ingredient = item.get();
        ingredient.setInStoke(!ingredient.isInStoke());
        return ingredientsItemRepository.save(ingredient);
    }
}
