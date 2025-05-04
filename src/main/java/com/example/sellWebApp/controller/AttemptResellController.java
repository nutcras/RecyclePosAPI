package com.example.sellWebApp.controller;


import com.example.sellWebApp.dto.AttemptResellDto;
import com.example.sellWebApp.dto.PageDto;
import com.example.sellWebApp.dto.SaleDto;
import com.example.sellWebApp.dto.criteria.AttemptResellSearchCriteria;
import com.example.sellWebApp.dto.criteria.EmployeeSearchCriteria;
import com.example.sellWebApp.dto.criteria.PageCriteria;
import com.example.sellWebApp.entity.AttemptResell;
import com.example.sellWebApp.service.AttemptResellService;
import com.example.sellWebApp.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/attemptResells")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AttemptResellController {

    @Autowired
    private AttemptResellService service;

    @PostMapping("/create")
    public ResponseEntity<AttemptResellDto> createSale() {
        return ResponseEntity.ok(service.createAttemptResell());
    }

    @PostMapping("/get/all")
    public ResponseEntity<PageDto<AttemptResellDto>> getAll(@RequestBody PageCriteria<AttemptResellSearchCriteria> condition){
        return ResponseEntity.ok(service.getAll(condition));
    }
}
