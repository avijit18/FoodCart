package com.FoodCart.Controllers;

import com.FoodCart.Entities.Category;
import com.FoodCart.Entities.UserEntity;
import com.FoodCart.Exceptions.RestaurantException;
import com.FoodCart.Exceptions.UserException;
import com.FoodCart.Services.Interfaces.CategoryService;
import com.FoodCart.Services.Interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final UserService userService;

    @PostMapping("/admin/category")
    public ResponseEntity<Category> createdCategory(
            @RequestHeader("Authorization") String jwt,
            @RequestBody Category category) throws RestaurantException, UserException {
        UserEntity user = userService.findUserProfileByJwt(jwt);

        Category createdCategory = categoryService.createCategory(category.getName(), user.getId());
        return new ResponseEntity<Category>(createdCategory, HttpStatus.OK);
    }

    @GetMapping("/category/restaurant/{id}")
    public ResponseEntity<List<Category>> getRestaurantsCategory(
            @PathVariable Long id,
            @RequestHeader("Authorization") String jwt) throws RestaurantException, UserException {
        UserEntity user = userService.findUserProfileByJwt(jwt);
        List<Category> categories = categoryService.findCategoryByRestaurantId(id);
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
}
