package com.FoodCart.Requests;

import lombok.Data;

@Data
public class ResetPasswordRequest {
    private String password;
    private String token;
}
