package com.example.sellWebApp.controller.common;


import com.example.sellWebApp.dto.PageDto;
import com.example.sellWebApp.dto.criteria.PageCriteria;
import com.example.sellWebApp.dto.criteria.SearchCriteria;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


public interface QueryController<T, C extends SearchCriteria> {

    @PostMapping("/get/all")
    ResponseEntity<PageDto<T>> getAll(@RequestBody PageCriteria<C> condition);

}
