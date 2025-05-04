package com.example.sellWebApp.dto.common;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class DtoExtend {
    private String function;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdateDate;
}
