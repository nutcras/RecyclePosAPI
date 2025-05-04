package com.example.sellWebApp.dto;

import com.example.sellWebApp.dto.common.DtoExtend;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class SaleDetailDto extends DtoExtend {
    private Long saleDetailId;
    private Long sale;
    private Long productId;
    private Float weight;
    private Float quantity;
    private Float productAmount;

}
