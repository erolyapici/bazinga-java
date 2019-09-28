package com.bazinga.auth.model.dto;

import com.bazinga.base.model.dto.BaseDto;
import lombok.Data;

@Data
public class AuthResponse extends BaseDto {
    private String token;

    public AuthResponse(String token) {
        this.token = token;
    }
}
