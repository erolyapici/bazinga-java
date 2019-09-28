package com.bazinga.base.model.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Data
public class EndpointResponse<T extends BaseDto> implements Serializable {
    private HttpStatus status = HttpStatus.OK;
    private String elapsedTime;
    private T data;
    private ErrorMessage errorMessage;
}
