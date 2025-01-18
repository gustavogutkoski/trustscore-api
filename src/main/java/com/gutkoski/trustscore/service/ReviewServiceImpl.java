package com.gutkoski.trustscore.service;

import com.gutkoski.trustscore.entity.Review;
import com.gutkoski.trustscore.repository.ReviewRepository;
import com.gutkoski.trustscore.service.interfaces.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Review createReview(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public Review getReviewById(Long id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found with id " + id));
    }

    @Override
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    @Override
    public Review updateReview(Long id, Review review) {
        Review existingReview = getReviewById(id);
//        existingReview.
        return null;
    }

    @Override
    public void deleteReview(Long id) {

    }
}
