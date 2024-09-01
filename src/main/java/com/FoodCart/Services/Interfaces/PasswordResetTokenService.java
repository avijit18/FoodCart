package com.FoodCart.Services.Interfaces;

import com.FoodCart.Entities.PasswordResetToken;

public interface PasswordResetTokenService {
    public PasswordResetToken findByToken(String token);

    public void delete(PasswordResetToken resetToken);
}
