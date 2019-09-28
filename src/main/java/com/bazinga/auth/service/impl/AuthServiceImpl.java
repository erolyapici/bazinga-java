package com.bazinga.auth.service.impl;

import com.bazinga.auth.model.dto.AuthResponse;
import com.bazinga.auth.model.dto.UserDetails;
import com.bazinga.auth.model.request.AuthRequest;
import com.bazinga.auth.model.util.JwtUtil;
import com.bazinga.auth.service.AuthService;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthServiceImpl implements AuthService {

    private JwtUtil jwtUtil;

    public AuthServiceImpl(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Optional<AuthResponse> getAuth(AuthRequest request) {

        UserDetails userDetails = new UserDetails(request.getUsername(), request.getPassword());
        String token = jwtUtil.generateToken(userDetails);
        AuthResponse authResponse = new AuthResponse(token);
        return Optional.of(authResponse);
    }
}
