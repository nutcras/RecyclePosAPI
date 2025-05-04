package com.example.sellWebApp.dto;

import com.example.sellWebApp.dto.common.DtoExtend;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ProductDto extends DtoExtend {
    private Long productId;
    private String name;
    private Float amount;
    private Float amountMember;
    private String unit;
}
