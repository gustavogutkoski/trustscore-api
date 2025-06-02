package com.gutkoski.trustscore.entity.spec;

import com.gutkoski.trustscore.entity.filter.FilterSpecificationFunction;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

/**
 * A generic builder class for creating JPA {@link Specification} instances using a dynamic filter.
 *
 * <p>This class allows the construction of type-safe and reusable JPA Specifications based on
 * a provided filter DTO and a mapping function that converts the filter into a list of predicates.</p>
 *
 * <p>The main goal is to encapsulate and abstract the logic of converting filters into
 * JPA criteria predicates, making it easier to build flexible query mechanisms
 * without duplicating boilerplate code.</p>
 *
 * @param <T> The entity type to which the Specification will be applied.
 * @param <F> The filter type (usually a DTO) containing the filtering parameters.
 */
public class GenericSpecBuilder<T, F> {

    private final F filter;
    private final FilterSpecificationFunction<F, T> filterSpecFunction;

    /**
     * Constructs a new {@code GenericSpecBuilder} instance.
     *
     * @param filter the filter DTO containing the query parameters
     * @param filterSpecFunction a function that converts the filter into an array of {@link jakarta.persistence.criteria.Predicate}
     */
    public GenericSpecBuilder(F filter, FilterSpecificationFunction<F, T> filterSpecFunction) {
        this.filter = filter;
        this.filterSpecFunction = filterSpecFunction;
    }

    /**
     * Builds a {@link Specification} instance using the provided filter and function.
     *
     * @return a {@link Specification} that can be used in repository queries
     */
    public Specification<T> build() {
        return (Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) ->
                cb.and(filterSpecFunction.apply(filter, root, query, cb));
    }
}


