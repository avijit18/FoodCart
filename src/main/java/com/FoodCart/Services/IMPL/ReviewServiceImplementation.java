package com.FoodCart.Services.IMPL;

import com.FoodCart.Entities.Restaurant;
import com.FoodCart.Entities.Review;
import com.FoodCart.Entities.UserEntity;
import com.FoodCart.Exceptions.ReviewException;
import com.FoodCart.Repositories.RestaurantRepository;
import com.FoodCart.Repositories.ReviewRepository;
import com.FoodCart.Requests.ReviewRequest;
import com.FoodCart.Services.Interfaces.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewServiceImplementation implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final RestaurantRepository restaurantRepository;

    @Override
    public Review submitReview(ReviewRequest reviewRequest, UserEntity user) {
        Review review = new Review();
        System.out.println(review);

        System.out.println(reviewRequest.getRestaurantId());
        Optional<Restaurant> restaurant = restaurantRepository.findById(reviewRequest.getRestaurantId());
        if (restaurant.isPresent()) {
            review.setRestaurant(restaurant.get());
        }
        review.setCustomer(user);
        review.setMessage(reviewRequest.getReviewText());
        review.setRating(reviewRequest.getRating());
        review.setCreatedAt(LocalDateTime.now());

        return reviewRepository.save(review);
    }

    @Override
    public void deleteReview(Long reviewId) throws ReviewException {
        Optional<Review> optionalReview = reviewRepository.findById(reviewId);

        if (optionalReview.isPresent()) {
            reviewRepository.deleteById(reviewId);
        } else {
            throw new ReviewException("Review with ID " + reviewId + " not found");
        }
    }

    @Override
    public double calculateAverageRating(List<Review> reviews) {
        double totalRating = 0;
        for (Review review : reviews) {
            totalRating += review.getRating();
        }
        if (reviews.size() > 0) {
            return totalRating / reviews.size();
        } else {
            return 0;
        }
    }
}
