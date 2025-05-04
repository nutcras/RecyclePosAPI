package com.example.sellWebApp.controller.common;


import com.example.sellWebApp.dto.criteria.SearchCriteria;

public interface MasterController<T, K, C extends SearchCriteria> extends CrudController<T, K>, QueryController<T, C> {
}
