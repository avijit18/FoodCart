package com.FoodCart.Services.IMPL;

import com.FoodCart.Entities.Notification;
import com.FoodCart.Entities.Order;
import com.FoodCart.Entities.Restaurant;
import com.FoodCart.Entities.UserEntity;
import com.FoodCart.Repositories.NotificationRepository;
import com.FoodCart.Services.Interfaces.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImplementation implements NotificationService {

    private final NotificationRepository notificationRepository;

    @Override
    public Notification sendOrderStatusNotification(Order order) {
        Notification notification = new Notification();
        notification.setMessage("your order is " + order.getOrderStatus() + " order id is - " + order.getId());
        notification.setCustomer(order.getCustomer());
        notification.setSentAt(new Date());

        return notificationRepository.save(notification);
    }

    @Override
    public void sendRestaurantNotification(Restaurant restaurant, String message) {
        // TODO:
    }

    @Override
    public void sendPromotionalNotification(UserEntity user, String message) {
        // TODO:
    }

    @Override
    public List<Notification> findUsersNotification(Long userId) {
        return notificationRepository.findByCustomerId(userId);
    }
}
