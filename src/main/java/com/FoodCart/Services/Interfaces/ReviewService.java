package com.FoodCart.Services.Interfaces;

import com.FoodCart.Entities.Review;
import com.FoodCart.Entities.UserEntity;
import com.FoodCart.Exceptions.ReviewException;
import com.FoodCart.Requests.ReviewRequest;

import java.util.List;

public interface ReviewService {
    public Review submitReview(ReviewRequest reviewRequest, UserEntity user);
    public void deleteReview(Long reviewId) throws ReviewException;
    public double calculateAverageRating(List<Review> reviews);
}
