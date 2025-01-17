package com.gutkoski.trustscore.entities.review;

import java.util.List;

public interface ReviewService {
    Review createReview(Review review);
    Review getReviewById(Long id);
    List<Review> getAllReviews();
    Review updateReview(Long id, Review review);
    void deleteReview(Long id);
}
