package com.FoodCart.Services.IMPL;

import com.FoodCart.Entities.PasswordResetToken;
import com.FoodCart.Repositories.PasswordResetTokenRepository;
import com.FoodCart.Services.Interfaces.PasswordResetTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PasswordResetTokenServiceImplementation implements PasswordResetTokenService {

    private final PasswordResetTokenRepository passwordResetTokenRepository;

    @Override
    public PasswordResetToken findByToken(String token) {
        PasswordResetToken passwordResetToken =passwordResetTokenRepository.findByToken(token);
        return passwordResetToken;
    }

    @Override
    public void delete(PasswordResetToken resetToken) {
        passwordResetTokenRepository.delete(resetToken);
    }
}
