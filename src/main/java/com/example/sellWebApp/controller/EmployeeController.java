package com.example.sellWebApp.controller;

import com.example.sellWebApp.controller.common.MasterController;
import com.example.sellWebApp.dto.EmployeeDto;
import com.example.sellWebApp.dto.PageDto;
import com.example.sellWebApp.dto.criteria.EmployeeSearchCriteria;
import com.example.sellWebApp.dto.criteria.PageCriteria;
import com.example.sellWebApp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/employee")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EmployeeController implements MasterController<EmployeeDto, Long, EmployeeSearchCriteria> {


    @Autowired
    private EmployeeService service;

    @Override
    public ResponseEntity<EmployeeDto> getById(Long id) throws IOException {
        return ResponseEntity.ok(service.getById(id));
    }

    @Override
    public ResponseEntity<EmployeeDto> create(EmployeeDto model) {
        return ResponseEntity.ok(service.create(model));
    }

    @Override
    public ResponseEntity<EmployeeDto> update(EmployeeDto model) {
        return ResponseEntity.ok(service.update(model));
    }

    @Override
    public ResponseEntity<Void> deleteById(Long id) {
        service.deleteById(id);
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<PageDto<EmployeeDto>> getAll(PageCriteria<EmployeeSearchCriteria> condition) {
        return ResponseEntity.ok(service.getAll(condition));
    }
}
