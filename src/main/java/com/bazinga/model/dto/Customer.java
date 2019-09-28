package com.bazinga.model.dto;

import com.bazinga.base.model.dto.BaseDto;
import lombok.Data;

@Data
public class Customer extends BaseDto {

    private String name;
    private String lastName;
}
