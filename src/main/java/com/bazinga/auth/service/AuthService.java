package com.bazinga.auth.service;

import com.bazinga.auth.model.dto.AuthResponse;
import com.bazinga.auth.model.request.AuthRequest;
import com.bazinga.base.model.dto.BaseDto;

import java.util.Optional;

public interface AuthService {
    Optional<AuthResponse> getAuth(AuthRequest request);

    Optional<String> getAuthenticationUserName(String token);
}
