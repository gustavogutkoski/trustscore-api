package com.gutkoski.trustscore.controller;

import com.gutkoski.trustscore.dto.ReviewRequestDTO;
import com.gutkoski.trustscore.dto.ReviewResponseDTO;
import com.gutkoski.trustscore.entity.Review;
import com.gutkoski.trustscore.entity.filter.ReviewFilterDTO;
import com.gutkoski.trustscore.service.interfaces.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    public ResponseEntity<ReviewResponseDTO> createReview(@RequestBody ReviewRequestDTO reviewRequestDTO) {
        ReviewResponseDTO createdReview = reviewService.createReview(reviewRequestDTO);
        return new ResponseEntity<>(
                createdReview,
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewResponseDTO> getReviewById(@PathVariable Long id) {
        ReviewResponseDTO review = reviewService.getReviewById(id);
        return ResponseEntity.ok(review);
    }

    @GetMapping
    public ResponseEntity<List<ReviewResponseDTO>> getAllReviews() {
        return ResponseEntity.ok(reviewService.getAllReviews());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReviewResponseDTO> updateReview(
            @PathVariable Long id,
            @RequestBody ReviewRequestDTO updatedReview
    ) {
        ReviewResponseDTO reviewResponseDTO = reviewService.updateReview(id, updatedReview);
        return ResponseEntity.ok(reviewResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<ReviewResponseDTO>> getFilteredReviews(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Integer rating,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate createdAfter,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate createdBefore
    ) {
        ReviewFilterDTO filter = new ReviewFilterDTO(title, rating, createdAfter, createdBefore);
        List<ReviewResponseDTO> reviews = reviewService.filterReviews(filter);
        return ResponseEntity.ok(reviews);
    }
}
