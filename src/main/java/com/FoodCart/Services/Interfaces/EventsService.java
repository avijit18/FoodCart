package com.FoodCart.Services.Interfaces;

import com.FoodCart.Entities.Events;
import com.FoodCart.Exceptions.RestaurantException;

import java.util.List;

public interface EventsService {

    public Events createEvent(Events event,Long restaurantId) throws RestaurantException;

    public List<Events> findAllEvent();

    public List<Events> findRestaurantsEvent(Long id);

    public void deleteEvent(Long id) throws Exception;

    public Events findById(Long id) throws Exception;
}
