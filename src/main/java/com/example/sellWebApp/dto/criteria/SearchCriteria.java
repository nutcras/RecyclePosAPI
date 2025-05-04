package com.example.sellWebApp.dto.criteria;

import org.springframework.data.jpa.domain.Specification;

/**
 * @param <T>
 */
public interface SearchCriteria<T> {
    Specification<T> getSpecification();
}
