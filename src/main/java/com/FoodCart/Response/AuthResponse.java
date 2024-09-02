package com.FoodCart.Response;

import com.FoodCart.Entities.Enums.USER_ROLE;
import lombok.Data;

@Data
public class AuthResponse {
    private String message;
    private String jwt;
    private USER_ROLE role;
}
