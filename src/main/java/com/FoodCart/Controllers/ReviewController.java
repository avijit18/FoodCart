package com.FoodCart.Controllers;

import com.FoodCart.Entities.Review;
import com.FoodCart.Entities.UserEntity;
import com.FoodCart.Exceptions.ReviewException;
import com.FoodCart.Exceptions.UserException;
import com.FoodCart.Requests.ReviewRequest;
import com.FoodCart.Services.Interfaces.ReviewService;
import com.FoodCart.Services.Interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final UserService userService;

    @PostMapping("/review")
    public ResponseEntity<Review> createReview(@RequestBody ReviewRequest review,
                                               @RequestHeader("Authorization") String jwt)
            throws UserException {
        UserEntity user = userService.findUserProfileByJwt(jwt);
        Review submitedReview = reviewService.submitReview(review, user);
        return ResponseEntity.ok(submitedReview);
    }

    @DeleteMapping("/delete/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long reviewId) throws ReviewException {
        reviewService.deleteReview(reviewId);
        return new ResponseEntity<>("Review deleted successfully", HttpStatus.OK);
    }

    @GetMapping("/average-rating")
    public ResponseEntity<Double> calculateAverageRating(@RequestBody List<Review> reviews) {
        double averageRating = reviewService.calculateAverageRating(reviews);
        return new ResponseEntity<>(averageRating, HttpStatus.OK);
    }
}
