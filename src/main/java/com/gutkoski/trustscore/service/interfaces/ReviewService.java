package com.gutkoski.trustscore.service.interfaces;

import com.gutkoski.trustscore.entity.Review;

import java.util.List;

public interface ReviewService {
    Review createReview(Review review);
    Review getReviewById(Long id);
    List<Review> getAllReviews();
    Review updateReview(Long id, Review review);
    void deleteReview(Long id);
}
