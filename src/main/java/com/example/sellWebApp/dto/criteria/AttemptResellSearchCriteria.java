package com.example.sellWebApp.dto.criteria;


import com.example.sellWebApp.entity.Product;
import jakarta.persistence.criteria.Predicate;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class AttemptResellSearchCriteria implements SearchCriteria<Product> {
    private String searchCondition;
    private Date date;

    public Specification<Product> getSpecification() {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.notEqual(root.get("function"), "D"));
            predicates.add(cb.lessThanOrEqualTo(root.get("startDate"), date));
            predicates.add(cb.greaterThanOrEqualTo(root.get("endDate"), date));
            return cb.and(predicates.toArray(new Predicate[0]));
        };

    }
}
