package com.FoodCart.Repositories;

import com.FoodCart.Entities.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
    List<Food> findByRestaurantId(Long restaurantId);

    @Query("SELECT f FROM Food f WHERE "
            + "f.name LIKE %:keyword% OR "
            + "f.foodCategory.name LIKE %:keyword% AND "
            + "f.restaurant!=null"
    )
    List<Food> searchByNameOrCategory(@Param("keyword") String keyword);
}
