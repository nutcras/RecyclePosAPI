package com.example.sellWebApp.controller;


import com.example.sellWebApp.dto.ProductDto;
import com.example.sellWebApp.dto.SaleDto;
import com.example.sellWebApp.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/sales")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SaleController {

    @Autowired
    private SaleService service;

    @PostMapping("/create")
    public ResponseEntity<SaleDto> createSale(@RequestBody SaleDto request) {
        return ResponseEntity.ok(service.create(request));
    }

    @PostMapping("/getReport")
    public ResponseEntity<byte[]> getReport(@RequestBody SaleDto request){
        return ResponseEntity.ok(service.getReport(request));
    }
}
