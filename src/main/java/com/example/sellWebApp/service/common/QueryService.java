package com.example.sellWebApp.service.common;


import com.example.sellWebApp.dto.PageDto;
import com.example.sellWebApp.dto.criteria.PageCriteria;
import com.example.sellWebApp.dto.criteria.SearchCriteria;

public interface QueryService<T, C extends SearchCriteria> {
    public PageDto<T> getAll(PageCriteria<C> condition);

}
