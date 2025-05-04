package com.example.sellWebApp.controller;

import com.example.sellWebApp.controller.common.MasterController;
import com.example.sellWebApp.dto.ProductDto;
import com.example.sellWebApp.dto.PageDto;
import com.example.sellWebApp.dto.ProductDto;
import com.example.sellWebApp.dto.criteria.MemberSearchCriteria;
import com.example.sellWebApp.dto.criteria.PageCriteria;
import com.example.sellWebApp.dto.criteria.ProductSearchCriteria;
import com.example.sellWebApp.service.MemberService;
import com.example.sellWebApp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/products")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController implements MasterController<ProductDto, Long, ProductSearchCriteria> {


    @Autowired
    private ProductService service;


    @Override
    public ResponseEntity<ProductDto> getById(Long id) throws IOException {
        return ResponseEntity.ok(service.getById(id));
    }

    @Override
    public ResponseEntity<ProductDto> create(ProductDto model) {
        return ResponseEntity.ok(service.create(model));
    }

    @Override
    public ResponseEntity<ProductDto> update(ProductDto model) {
        return ResponseEntity.ok(service.update(model));
    }

    @Override
    public ResponseEntity<Void> deleteById(Long id) {
        service.deleteById(id);
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<PageDto<ProductDto>> getAll(PageCriteria<ProductSearchCriteria> condition) {
        return ResponseEntity.ok(service.getAll(condition));
    }
}
