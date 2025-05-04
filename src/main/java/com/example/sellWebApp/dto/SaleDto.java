package com.example.sellWebApp.dto;

import com.example.sellWebApp.dto.common.DtoExtend;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class SaleDto extends DtoExtend {
    private Long saleId;
    private Long employeeId;
    private EmployeeDto employee;
    private Long memberId;
    private MemberDto member;
    private List<SaleDetailDto> details;
}
