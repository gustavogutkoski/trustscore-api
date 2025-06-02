package com.gutkoski.trustscore.entity.spec.review;

import com.gutkoski.trustscore.entity.Review;
import com.gutkoski.trustscore.entity.filter.ReviewFilterDTO;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides filtering logic for building dynamic JPA predicates for the {@link Review} entity.
 *
 * <p>This class defines a static method to convert a {@link ReviewFilterDTO} into an array of
 * {@link Predicate}, which can be used in a JPA {@link jakarta.persistence.criteria.CriteriaQuery}
 * for dynamic searching.
 *
 * <p>Typical usage involves passing this method as a method reference to a {@code
 * GenericSpecBuilder}, enabling clean and reusable specification construction.
 */
public class ReviewFilterSpecification {

  /**
   * Converts the provided {@link ReviewFilterDTO} into an array of {@link Predicate}s used to
   * filter {@link Review} entities in a criteria query.
   *
   * @param filter the filter data transfer object containing search parameters
   * @param root the root type in the {@code from} clause
   * @param query the criteria query being constructed
   * @param cb the criteria builder used to construct the predicates
   * @return an array of predicates based on the filter fields
   */
  public static Predicate[] toPredicates(
      ReviewFilterDTO filter, Root<Review> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
    List<Predicate> predicates = new ArrayList<>();

    addTitlePredicate(filter, root, cb, predicates);
    addRatingPredicate(filter, root, cb, predicates);
    addCreatedAfterPredicate(filter, root, cb, predicates);
    addCreatedBeforePredicate(filter, root, cb, predicates);

    return predicates.toArray(new Predicate[0]);
  }

  /** Adds a predicate for filtering by review title (case-insensitive partial match). */
  private static void addTitlePredicate(
      ReviewFilterDTO filter, Root<Review> root, CriteriaBuilder cb, List<Predicate> predicates) {
    if (filter.title() != null && !filter.title().isBlank()) {
      predicates.add(
          cb.like(cb.lower(root.get("title")), "%" + filter.title().toLowerCase() + "%"));
    }
  }

  /** Adds a predicate for filtering by exact rating. */
  private static void addRatingPredicate(
      ReviewFilterDTO filter, Root<Review> root, CriteriaBuilder cb, List<Predicate> predicates) {
    if (filter.rating() != null) {
      predicates.add(cb.equal(root.get("rating"), filter.rating()));
    }
  }

  /** Adds a predicate for filtering reviews created after or on a specific date. */
  private static void addCreatedAfterPredicate(
      ReviewFilterDTO filter, Root<Review> root, CriteriaBuilder cb, List<Predicate> predicates) {
    if (filter.createdAfter() != null) {
      predicates.add(cb.greaterThanOrEqualTo(root.get("createdAt"), filter.createdAfter()));
    }
  }

  /** Adds a predicate for filtering reviews created before or on a specific date. */
  private static void addCreatedBeforePredicate(
      ReviewFilterDTO filter, Root<Review> root, CriteriaBuilder cb, List<Predicate> predicates) {
    if (filter.createdBefore() != null) {
      predicates.add(cb.lessThanOrEqualTo(root.get("createdAt"), filter.createdBefore()));
    }
  }
}
