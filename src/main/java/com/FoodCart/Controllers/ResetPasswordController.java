package com.FoodCart.Controllers;

import com.FoodCart.Advices.ApiResponse;
import com.FoodCart.Entities.PasswordResetToken;
import com.FoodCart.Entities.UserEntity;
import com.FoodCart.Exceptions.UserException;
import com.FoodCart.Requests.ResetPasswordRequest;
import com.FoodCart.Services.Interfaces.PasswordResetTokenService;
import com.FoodCart.Services.Interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ResetPasswordController {

    private final PasswordResetTokenService passwordResetTokenService;
    private final UserService userService;

//    @PostMapping
//    public ResponseEntity<ApiResponse> resetPassword(
//
//            @RequestBody ResetPasswordRequest req) throws UserException {
//
//        PasswordResetToken resetToken = passwordResetTokenService.findByToken(req.getToken());
//
//        if (resetToken == null ) {
//            throw new UserException("token is required...");
//        }
//        if(resetToken.isExpired()) {
//            passwordResetTokenService.delete(resetToken);
//            throw new UserException("token get expired...");
//
//        }
//
//        // Update user's password
//        UserEntity user = resetToken.getUser();
//        userService.updatePassword(user, req.getPassword());
//
//        // Delete the token
//        passwordResetTokenService.delete(resetToken);
//
//        ApiResponse res=new ApiResponse();
////        res.setMessage("Password updated successfully.");
////        res.setStatus(true);
//
//        return ResponseEntity.ok(res);
//    }
//
//    @PostMapping("/reset")
//    public ResponseEntity<ApiResponse> resetPassword(@RequestParam("email") String email) throws UserException {
//        UserEntity user = userService.findUserByEmail(email);
//        System.out.println("ResetPasswordController.resetPassword()");
//
//        if (user == null) {
//            throw new UserException("user not found");
//        }
//
//        userService.sendPasswordResetEmail(user);
//
//        ApiResponse res=new ApiResponse();
////        res.setMessage("Password reset email sent successfully.");
////        res.setStatus(true);
//
//        return ResponseEntity.ok(res);
//    }
}
