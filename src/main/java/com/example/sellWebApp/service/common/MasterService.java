package com.example.sellWebApp.service.common;


import com.example.sellWebApp.dto.criteria.SearchCriteria;

public interface MasterService<T, K, C extends SearchCriteria> extends CrudService<T, K>, QueryService<T, C> {
}
