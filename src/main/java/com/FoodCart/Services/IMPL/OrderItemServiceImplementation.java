package com.FoodCart.Services.IMPL;

import com.FoodCart.Entities.OrderItem;
import com.FoodCart.Repositories.OrderItemRepository;
import com.FoodCart.Services.Interfaces.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImplementation implements OrderItemService {

    private final OrderItemRepository orderItemRepository;

    @Override
    public OrderItem createOrderIem(OrderItem orderItem) {
        OrderItem newOrderItem = new OrderItem();
//	    	newOrderItem.setMenuItem(orderItem.getMenuItem());
//	    	newOrderItem.setOrder(orderItem.getOrder());
        newOrderItem.setQuantity(orderItem.getQuantity());
        return orderItemRepository.save(newOrderItem);
    }
}
