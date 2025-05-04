package com.example.sellWebApp.controller;

import com.example.sellWebApp.controller.common.MasterController;
import com.example.sellWebApp.dto.EmployeeDto;
import com.example.sellWebApp.dto.MemberDto;
import com.example.sellWebApp.dto.PageDto;
import com.example.sellWebApp.dto.criteria.EmployeeSearchCriteria;
import com.example.sellWebApp.dto.criteria.MemberSearchCriteria;
import com.example.sellWebApp.dto.criteria.PageCriteria;
import com.example.sellWebApp.service.EmployeeService;
import com.example.sellWebApp.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/members")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MemberController implements MasterController<MemberDto, Long, MemberSearchCriteria> {


    @Autowired
    private MemberService service;


    @Override
    public ResponseEntity<MemberDto> getById(Long id) throws IOException {
        return ResponseEntity.ok(service.getById(id));
    }

    @Override
    public ResponseEntity<MemberDto> create(MemberDto model) {
        return ResponseEntity.ok(service.create(model));
    }

    @Override
    public ResponseEntity<MemberDto> update(MemberDto model) {
        return ResponseEntity.ok(service.update(model));
    }

    @Override
    public ResponseEntity<Void> deleteById(Long id) {
        service.deleteById(id);
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<PageDto<MemberDto>> getAll(PageCriteria<MemberSearchCriteria> condition) {
        return ResponseEntity.ok(service.getAll(condition));
    }
}
