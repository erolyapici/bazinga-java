package com.bazinga.base.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class ListingDto<T extends BaseDto> extends BaseDto {
    private Integer totalCount;
    private List<T> types;
}
