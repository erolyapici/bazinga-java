package com.bazinga.base.model.dto;

import lombok.Data;

@Data
public class ErrorMessage {
    private String code;
    private String message;

    public ErrorMessage(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
