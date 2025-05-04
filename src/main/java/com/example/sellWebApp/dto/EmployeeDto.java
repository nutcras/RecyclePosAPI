package com.example.sellWebApp.dto;

import com.example.sellWebApp.dto.common.DtoExtend;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;



@Setter
@Getter
public class EmployeeDto extends DtoExtend {
    private Long employeeId;
    private String username;
    private String password;
    private String name;
    private String role;
}
