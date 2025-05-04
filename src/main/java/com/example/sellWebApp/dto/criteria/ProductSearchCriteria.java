package com.example.sellWebApp.dto.criteria;


import com.example.sellWebApp.entity.Employee;
import com.example.sellWebApp.entity.Product;
import jakarta.persistence.criteria.Predicate;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProductSearchCriteria implements SearchCriteria<Product> {
    private String searchCondition;


    public Specification<Product> getSpecification() {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.notEqual(root.get("function"), "D"));
            predicates.add(
                    cb.like(cb.lower(root.get("name")), "%" + searchCondition.toLowerCase() + "%")
            );
            return cb.and(predicates.toArray(new Predicate[0]));
        };

    }
}
