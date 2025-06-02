package com.gutkoski.trustscore.service.interfaces;

import com.gutkoski.trustscore.dto.ReviewRequestDTO;
import com.gutkoski.trustscore.dto.ReviewResponseDTO;
import com.gutkoski.trustscore.entity.filter.ReviewFilterDTO;

import java.util.List;

public interface ReviewService {
    ReviewResponseDTO createReview(ReviewRequestDTO reviewRequestDTO);
    ReviewResponseDTO getReviewById(Long id);
    List<ReviewResponseDTO> getAllReviews();
    ReviewResponseDTO updateReview(Long id, ReviewRequestDTO reviewRequestDTO);
    void deleteReview(Long id);
    List<ReviewResponseDTO> filterReviews(ReviewFilterDTO filter);

}
