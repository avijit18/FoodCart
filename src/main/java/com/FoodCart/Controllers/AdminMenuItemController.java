package com.FoodCart.Controllers;

import com.FoodCart.Entities.Food;
import com.FoodCart.Entities.Restaurant;
import com.FoodCart.Entities.UserEntity;
import com.FoodCart.Exceptions.FoodException;
import com.FoodCart.Exceptions.RestaurantException;
import com.FoodCart.Exceptions.UserException;
import com.FoodCart.Requests.CreateFoodRequest;
import com.FoodCart.Services.Interfaces.FoodService;
import com.FoodCart.Services.Interfaces.RestaurantService;
import com.FoodCart.Services.Interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/food")
@RequiredArgsConstructor
public class AdminMenuItemController {

    private final FoodService menuItemService;
    private final UserService userService;
    private final RestaurantService restaurantService;

    @PostMapping()
    public ResponseEntity<Food> createItem(
            @RequestBody CreateFoodRequest item,
            @RequestHeader("Authorization") String jwt)
            throws FoodException, UserException, RestaurantException {
        System.out.println("req-controller ----" + item);
        UserEntity user = userService.findUserProfileByJwt(jwt);
//		Category category=categoryService.findCategoryById(item.getCategoryId());
        Restaurant restaurant = restaurantService.findRestaurantById(item.getRestaurantId());
        Food menuItem = menuItemService.createFood(item, item.getCategory(), restaurant);
        return ResponseEntity.ok(menuItem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable Long id, @RequestHeader("Authorization") String jwt)
            throws UserException, FoodException {
        UserEntity user = userService.findUserProfileByJwt(jwt);

        menuItemService.deleteFood(id);
        return ResponseEntity.ok("Menu item deleted");
    }

    @GetMapping("/search")
    public ResponseEntity<List<Food>> getMenuItemByName(@RequestParam String name) {
        List<Food> menuItem = menuItemService.searchFood(name);
        return ResponseEntity.ok(menuItem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Food> updateAvilibilityStatus(
            @PathVariable Long id) throws FoodException {
        Food menuItems = menuItemService.updateAvailibilityStatus(id);
        return ResponseEntity.ok(menuItems);
    }
}
