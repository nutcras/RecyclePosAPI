package com.example.sellWebApp.dto;

import com.example.sellWebApp.dto.common.DtoExtend;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class MemberDto extends DtoExtend {
    private Long memberId;
    private String name;
    private String phoneNumber;


}
