package com.example.sellWebApp.dto.criteria;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PageCriteria<C extends SearchCriteria> {
    private C condition;
    private int page = 1;
    private int size = 10;
    private Sort.Direction direction = Sort.DEFAULT_DIRECTION;
    private String sortBy;


    public PageRequest genPageRequest() {
        if(size >20){
            size = 20;
        }
        return StringUtils.hasLength(sortBy)
                ? PageRequest.of(page - 1, size, Sort.by(direction, sortBy))
                : PageRequest.of(page - 1, size);
    }

    public Specification getSpecification() {
        return condition.getSpecification();
    }
}
