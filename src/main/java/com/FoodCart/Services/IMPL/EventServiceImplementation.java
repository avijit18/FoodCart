package com.FoodCart.Services.IMPL;

import com.FoodCart.Entities.Events;
import com.FoodCart.Entities.Restaurant;
import com.FoodCart.Exceptions.RestaurantException;
import com.FoodCart.Repositories.EventRepository;
import com.FoodCart.Services.Interfaces.EventsService;
import com.FoodCart.Services.Interfaces.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventServiceImplementation implements EventsService {

    private final EventRepository eventRepository;
    private final RestaurantService restaurantService;

    @Override
    public Events createEvent(Events event, Long restaurantId) throws RestaurantException {
        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);

        Events createdEvent = new Events();
        createdEvent.setRestaurant(restaurant);
        createdEvent.setImage(event.getImage());
        createdEvent.setStartedAt(event.getStartedAt());
        createdEvent.setEndsAt(event.getEndsAt());
        createdEvent.setLocation(event.getLocation());
        createdEvent.setName(event.getName());

        return eventRepository.save(createdEvent);
    }

    @Override
    public List<Events> findAllEvent() {
        return eventRepository.findAll();
    }

    @Override
    public List<Events> findRestaurantsEvent(Long id) {
        return eventRepository.findEventsByRestaurantId(id);
    }

    @Override
    public void deleteEvent(Long id) throws Exception {
        Events event = findById(id);
        eventRepository.delete(event);
    }

    @Override
    public Events findById(Long id) throws Exception {
        Optional<Events> opt = eventRepository.findById(id);
        if (opt.isPresent()) {
            return opt.get();
        }
        throw new Exception("event not found withy id " + id);
    }
}
