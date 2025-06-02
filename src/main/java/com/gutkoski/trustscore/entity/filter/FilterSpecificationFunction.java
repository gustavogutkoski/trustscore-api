package com.gutkoski.trustscore.entity.filter;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

/**
 * Functional interface representing a function that converts a filter object into an array of JPA
 * {@link Predicate}.
 *
 * <p>This interface is used to abstract the logic of building dynamic query predicates from a
 * filter DTO. It allows the {@code GenericSpecBuilder} to remain generic and reusable by deferring
 * the filter-specific predicate construction to implementations of this interface.
 *
 * @param <F> the type of the filter object (usually a DTO containing query parameters)
 * @param <T> the type of the entity being queried
 */
@FunctionalInterface
public interface FilterSpecificationFunction<F, T> {

  /**
   * Converts the given filter into an array of {@link Predicate} to be applied in a JPA query.
   *
   * @param filter the filter object containing the query parameters
   * @param root the root type in the {@code from} clause
   * @param query the criteria query being constructed
   * @param cb the criteria builder used to construct predicates
   * @return an array of predicates to be applied in the {@link
   *     jakarta.persistence.criteria.CriteriaQuery}
   */
  Predicate[] apply(F filter, Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb);
}
