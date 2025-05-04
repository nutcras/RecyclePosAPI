package com.example.sellWebApp.dto;

import com.example.sellWebApp.dto.response.ResponseApi;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.util.List;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PageDto<T> {
    private List<T> data;
    private final ResponseApi.PageInfo pageInfo;



    public PageDto(List<T> data, int size, long totalElements, int totalPages, int page) {
        this.data = data;
        this.pageInfo = new ResponseApi.PageInfo(size, totalElements, totalPages, page);
    }
}
