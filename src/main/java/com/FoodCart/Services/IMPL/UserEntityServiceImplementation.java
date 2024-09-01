package com.FoodCart.Services.IMPL;

import com.FoodCart.Entities.PasswordResetToken;
import com.FoodCart.Entities.UserEntity;
import com.FoodCart.Exceptions.UserException;
import com.FoodCart.Repositories.PasswordResetTokenRepository;
import com.FoodCart.Repositories.UserRepository;
import com.FoodCart.Security.SecurityService.JwtService;
import com.FoodCart.Services.Interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserEntityServiceImplementation implements UserService {

    private final UserRepository userRepository;
    private final JwtService jwtProvider;
    private final PasswordEncoder passwordEncoder;
    private final PasswordResetTokenRepository passwordResetTokenRepository;
    private final JavaMailSender javaMailSender;

    @Override
    public UserEntity findUserProfileByJwt(String jwt) throws UserException {
        String email = jwtProvider.getEmailFromJwtToken(jwt);

        UserEntity user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UserException("user not exist with email " + email);
        }
//		System.out.println("email user "+user.get().getEmail());
        return user;
    }

    @Override
    public UserEntity findUserByEmail(String email) throws UserException {
        UserEntity user = userRepository.findByEmail(email);
        if (user != null) {
            return user;
        }
        throw new UserException("user not exist with username " + email);
    }

    @Override
    public List<UserEntity> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<UserEntity> getPendingRestaurantOwner() {
        return userRepository.getPendingRestaurantOwners();
    }

    @Override
    public void updatePassword(UserEntity user, String newPassword) {
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    @Override
    public void sendPasswordResetEmail(UserEntity user) {
        // Generate a random token (you might want to use a library for this)
        String resetToken = generateRandomToken();

        // Calculate expiry date
        Date expiryDate = calculateExpiryDate();

        // Save the token in the database
        PasswordResetToken passwordResetToken = new PasswordResetToken(resetToken, user, expiryDate);
        passwordResetTokenRepository.save(passwordResetToken);

        // Send an email containing the reset link
        sendEmail(user.getEmail(), "Password Reset",
                "Click the following link to reset your password: http://localhost:8080/account/reset-password?token="
                        + resetToken);
    }

    private void sendEmail(String to, String subject, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);

        javaMailSender.send(mailMessage);
    }

    private String generateRandomToken() {
        return UUID.randomUUID().toString();
    }

    private Date calculateExpiryDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MINUTE, 10);
        return cal.getTime();
    }
}
