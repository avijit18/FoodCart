package com.FoodCart.Services.IMPL;

import com.FoodCart.Entities.Category;
import com.FoodCart.Entities.Restaurant;
import com.FoodCart.Exceptions.RestaurantException;
import com.FoodCart.Repositories.CategoryRepository;
import com.FoodCart.Services.Interfaces.CategoryService;
import com.FoodCart.Services.Interfaces.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImplementation implements CategoryService {

    private final RestaurantService restaurantService;
    private final CategoryRepository categoryRepository;

    @Override
    public Category createCategory(String name, Long userId) throws RestaurantException {
        Restaurant restaurant=restaurantService.getRestaurantsByUserId(userId);
        Category createdCategory=new Category();

        createdCategory.setName(name);
        createdCategory.setRestaurant(restaurant);
        return categoryRepository.save(createdCategory);
    }

    @Override
    public List<Category> findCategoryByRestaurantId(Long restaurantId) throws RestaurantException {
        Restaurant restaurant=restaurantService.findRestaurantById(restaurantId);
        return categoryRepository.findByRestaurantId(restaurantId);
    }

    @Override
    public Category findCategoryById(Long id) throws RestaurantException {
        Optional<Category> opt=categoryRepository.findById(id);
        if(opt.isEmpty()) {
            throw new RestaurantException("category not exist with id "+id);
        }
        return opt.get();
    }
}
