package com.gutkoski.trustscore.service;

import com.gutkoski.trustscore.dto.ReviewRequestDTO;
import com.gutkoski.trustscore.dto.ReviewResponseDTO;
import com.gutkoski.trustscore.entity.Product;
import com.gutkoski.trustscore.entity.Review;
import com.gutkoski.trustscore.entity.User;
import com.gutkoski.trustscore.entity.filter.ReviewFilterDTO;
import com.gutkoski.trustscore.entity.spec.GenericSpecBuilder;
import com.gutkoski.trustscore.entity.spec.review.ReviewFilterSpecification;
import com.gutkoski.trustscore.mapper.ReviewMapper;
import com.gutkoski.trustscore.repository.ProductRepository;
import com.gutkoski.trustscore.repository.ReviewRepository;
import com.gutkoski.trustscore.repository.UserRepository;
import com.gutkoski.trustscore.service.interfaces.ReviewService;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {

  private final ReviewRepository reviewRepository;
  private final ReviewMapper reviewMapper;
  private final UserRepository userRepository;
  private final ProductRepository productRepository;

  public ReviewServiceImpl(
      ReviewRepository reviewRepository,
      ReviewMapper reviewMapper,
      UserRepository userRepository,
      ProductRepository productRepository) {
    this.reviewRepository = reviewRepository;
    this.reviewMapper = reviewMapper;
    this.userRepository = userRepository;
    this.productRepository = productRepository;
  }

  @Override
  public ReviewResponseDTO createReview(ReviewRequestDTO reviewRequestDTO) {
    Review review = reviewMapper.toEntity(reviewRequestDTO);
    return reviewMapper.toDTO(reviewRepository.save(review));
  }

  @Override
  public ReviewResponseDTO getReviewById(Long id) {
    Review existingReview =
        reviewRepository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("Review not found with id " + id));
    return reviewMapper.toDTO(existingReview);
  }

  @Override
  public List<ReviewResponseDTO> getAllReviews() {
    return reviewRepository.findAll().stream().map(reviewMapper::toDTO).toList();
  }

  @Override
  public ReviewResponseDTO updateReview(Long id, ReviewRequestDTO reviewRequestDTO) {
    Review existingReview =
        reviewRepository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("Review not found with id " + id));
    existingReview.setComment(reviewRequestDTO.comment());
    existingReview.setCreatedAt(reviewRequestDTO.createdAt());
    existingReview.setRating(reviewRequestDTO.rating());

    User user =
        userRepository
            .findById(reviewRequestDTO.userId())
            .orElseThrow(
                () -> new RuntimeException("User not found with id " + reviewRequestDTO.userId()));
    Product product =
        productRepository
            .findById(reviewRequestDTO.productId())
            .orElseThrow(
                () ->
                    new RuntimeException(
                        "Product not found with id " + reviewRequestDTO.productId()));

    existingReview.setUser(user);
    existingReview.setProduct(product);
    return reviewMapper.toDTO(reviewRepository.save(existingReview));
  }

  @Override
  public void deleteReview(Long id) {
    reviewRepository.deleteById(id);
  }

  @Override
  public List<ReviewResponseDTO> filterReviews(ReviewFilterDTO filter) {
    Specification<Review> spec =
        new GenericSpecBuilder<>(filter, ReviewFilterSpecification::toPredicates).build();

    return reviewRepository.findAll(spec).stream().map(reviewMapper::toDTO).toList();
  }
}
