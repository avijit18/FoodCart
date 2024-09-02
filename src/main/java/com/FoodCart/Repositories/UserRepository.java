package com.FoodCart.Repositories;

import com.FoodCart.Entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query("SELECT u FROM UserEntity u Where u.status='PENDING'")
    public List<UserEntity> getPendingRestaurantOwners();

    public UserEntity findByEmail(String username);
}
