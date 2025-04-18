package com.gutkoski.trustscore.entity.spec;

import com.gutkoski.trustscore.entity.Review;
import com.gutkoski.trustscore.entity.filter.ReviewFilterDTO;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

/**
 * Builds a dynamic {@link Specification} for filtering {@link Review} entities
 * based on the provided {@link ReviewFilterDTO} criteria.
 */
public class ReviewSpecBuilder {

    /**
     * Builds a specification to filter reviews according to the given filter parameters.
     *
     * @param filter an object containing filter parameters such as title, rating, and created date range
     * @return a {@link Specification} to be used in Spring Data JPA queries
     */
    public static Specification<Review> build(ReviewFilterDTO filter) {
        return (Root<Review> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            addTitlePredicate(filter, root, cb, predicates);
            addRatingPredicate(filter, root, cb, predicates);
            addCreatedAfterPredicate(filter, root, cb, predicates);
            addCreatedBeforePredicate(filter, root, cb, predicates);

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

    /**
     * Adds a predicate for filtering by review title (case-insensitive, partial match).
     */
    private static void addTitlePredicate(ReviewFilterDTO filter, Root<Review> root, CriteriaBuilder cb, List<Predicate> predicates) {
        if (filter.title() != null && !filter.title().isBlank()) {
            predicates.add(cb.like(cb.lower(root.get("title")), "%" + filter.title().toLowerCase() + "%"));
        }
    }

    /**
     * Adds a predicate for filtering by exact review rating.
     */
    private static void addRatingPredicate(ReviewFilterDTO filter, Root<Review> root, CriteriaBuilder cb, List<Predicate> predicates) {
        if (filter.rating() != null) {
            predicates.add(cb.equal(root.get("rating"), filter.rating()));
        }
    }

    /**
     * Adds a predicate for filtering reviews created after or on the specified date.
     */
    private static void addCreatedAfterPredicate(ReviewFilterDTO filter, Root<Review> root, CriteriaBuilder cb, List<Predicate> predicates) {
        if (filter.createdAfter() != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("createdAt"), filter.createdAfter()));
        }
    }

    /**
     * Adds a predicate for filtering reviews created before or on the specified date.
     */
    private static void addCreatedBeforePredicate(ReviewFilterDTO filter, Root<Review> root, CriteriaBuilder cb, List<Predicate> predicates) {
        if (filter.createdBefore() != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get("createdAt"), filter.createdBefore()));
        }
    }
}
