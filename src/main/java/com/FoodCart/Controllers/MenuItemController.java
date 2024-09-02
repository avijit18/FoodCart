package com.FoodCart.Controllers;

import com.FoodCart.Entities.Food;
import com.FoodCart.Exceptions.FoodException;
import com.FoodCart.Services.Interfaces.FoodService;
import com.FoodCart.Services.Interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food")
@RequiredArgsConstructor
public class MenuItemController {

    private final FoodService menuItemService;
    private final UserService userService;

    @GetMapping("/search")
    public ResponseEntity<List<Food>> searchFood(
            @RequestParam String name) {
        List<Food> menuItem = menuItemService.searchFood(name);
        return ResponseEntity.ok(menuItem);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<Food>> getMenuItemByRestaurantId(
            @PathVariable Long restaurantId,
            @RequestParam boolean vegetarian,
            @RequestParam boolean seasonal,
            @RequestParam boolean nonveg,
            @RequestParam(required = false) String food_category) throws FoodException {
        List<Food> menuItems = menuItemService.getRestaurantsFood(
                restaurantId, vegetarian, nonveg, seasonal, food_category);
        return ResponseEntity.ok(menuItems);
    }
}
