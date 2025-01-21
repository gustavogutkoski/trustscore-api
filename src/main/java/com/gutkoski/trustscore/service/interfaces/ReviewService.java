package com.gutkoski.trustscore.service.interfaces;

import com.gutkoski.trustscore.dto.ReviewRequestDTO;
import com.gutkoski.trustscore.dto.ReviewResponseDTO;

import java.util.List;

public interface ReviewService {
    ReviewResponseDTO createReview(ReviewRequestDTO reviewRequestDTO);
    ReviewResponseDTO getReviewById(Long id);
    List<ReviewResponseDTO> getAllReviews();
    ReviewResponseDTO updateReview(Long id, ReviewRequestDTO reviewRequestDTO);
    void deleteReview(Long id);
}
