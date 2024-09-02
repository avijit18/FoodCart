package com.FoodCart.Controllers;

import com.FoodCart.Entities.UserEntity;
import com.FoodCart.Services.Interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SupperAdminController {

    private final UserService userService;

    @GetMapping("/api/customers")
    public ResponseEntity<List<UserEntity>> getAllCustomers() {
        List<UserEntity> users = userService.findAllUsers();
        return new ResponseEntity<>(users, HttpStatus.ACCEPTED);
    }

    @GetMapping("/api/pending-customers")
    public ResponseEntity<List<UserEntity>> getPendingRestaurantUser() {
        List<UserEntity> users = userService.getPendingRestaurantOwner();
        return new ResponseEntity<List<UserEntity>>(users, HttpStatus.ACCEPTED);
    }
}
