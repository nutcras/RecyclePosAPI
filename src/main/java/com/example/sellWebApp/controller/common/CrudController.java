package com.example.sellWebApp.controller.common;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;


public interface CrudController<T, K> {

    @PostMapping("/get/id")
    ResponseEntity<T> getById(@RequestBody K id) throws IOException;

    @PostMapping("/create")
    ResponseEntity<T> create(@RequestBody T model);

    @PostMapping("/update")
    ResponseEntity<T> update(@RequestBody T model);

    @PostMapping("/delete")
    ResponseEntity<Void> deleteById(@RequestBody K id);

}
