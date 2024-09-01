package com.FoodCart.Services.Interfaces;

import com.FoodCart.Entities.UserEntity;
import com.FoodCart.Exceptions.UserException;

import java.util.List;

public interface UserService {
    public UserEntity findUserProfileByJwt(String jwt) throws UserException;

    public UserEntity findUserByEmail(String email) throws UserException;

    public List<UserEntity> findAllUsers();

    public List<UserEntity> getPendingRestaurantOwner();

    void updatePassword(UserEntity user, String newPassword);

    void sendPasswordResetEmail(UserEntity user);
}
