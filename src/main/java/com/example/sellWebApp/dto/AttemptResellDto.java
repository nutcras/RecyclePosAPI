package com.example.sellWebApp.dto;

import com.example.sellWebApp.dto.common.DtoExtend;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@Getter
@Setter
public class AttemptResellDto extends DtoExtend {
    private Long id;
    private Date startDate;
    private Date endDate;
    private Double amount;
    private Double weight;
    private Double quantity;
}
