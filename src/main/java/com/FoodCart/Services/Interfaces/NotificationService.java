package com.FoodCart.Services.Interfaces;

import com.FoodCart.Entities.Notification;
import com.FoodCart.Entities.Order;
import com.FoodCart.Entities.Restaurant;
import com.FoodCart.Entities.UserEntity;

import java.util.List;

public interface NotificationService {
    public Notification sendOrderStatusNotification(Order order);
    public void sendRestaurantNotification(Restaurant restaurant, String message);
    public void sendPromotionalNotification(UserEntity user, String message);

    public List<Notification> findUsersNotification(Long userId);
}
