package com.FoodCart.Controllers;

import com.FoodCart.Entities.Notification;
import com.FoodCart.Entities.UserEntity;
import com.FoodCart.Exceptions.UserException;
import com.FoodCart.Services.Interfaces.NotificationService;
import com.FoodCart.Services.Interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;
    private final UserService userService;

    @GetMapping("/notifications")
    public ResponseEntity<List<Notification>> findUsersNotification(
            @RequestHeader("Authorization") String jwt) throws UserException {
        UserEntity user = userService.findUserProfileByJwt(jwt);

        List<Notification> notifications = notificationService.findUsersNotification(user.getId());
        return new ResponseEntity<List<Notification>>(notifications, HttpStatus.ACCEPTED);
    }
}
