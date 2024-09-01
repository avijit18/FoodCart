package com.FoodCart.Repositories;

import com.FoodCart.Entities.IngredientCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientsCategoryRepository extends JpaRepository<IngredientCategory, Long> {
    List<IngredientCategory> findByRestaurantId(Long id);

    @Query("SELECT e FROM IngredientCategory e "
            + "WHERE e.restaurant.id = :restaurantId "
            + "AND lower(e.name) = lower(:name)")
    IngredientCategory findByRestaurantIdAndNameIgnoreCase(
            @Param("restaurantId") Long restaurantId, @Param("name") String name);
}
