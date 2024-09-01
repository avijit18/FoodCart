package com.FoodCart.Repositories;


import com.FoodCart.Entities.Events;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Events, Long> {
    public List<Events> findEventsByRestaurantId(Long id);
}
