package com.bazinga.auth.service.impl;

import com.bazinga.auth.model.ClaimName;
import com.bazinga.auth.model.dto.AuthResponse;
import com.bazinga.auth.model.request.AuthRequest;
import com.bazinga.auth.model.util.JwtUtil;
import com.bazinga.auth.service.AuthService;
import com.bazinga.base.exception.ServiceException;
import com.bazinga.base.model.dto.ErrorCode;
import com.bazinga.user.model.dto.UserDto;
import com.bazinga.user.service.UserService;
import com.bazinga.user.util.UserPasswordUtil;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class AuthServiceImpl implements AuthService {

    private JwtUtil jwtUtil;
    private UserService userService;
    private UserPasswordUtil userPasswordUtil;

    public AuthServiceImpl(JwtUtil jwtUtil, UserService userService, UserPasswordUtil userPasswordUtil) {
        this.jwtUtil = jwtUtil;
        this.userService = userService;
        this.userPasswordUtil = userPasswordUtil;
    }

    @Override
    public Optional<AuthResponse> getAuth(AuthRequest request) {
        Optional<UserDto> userByUserName = userService.getUserByUserName(request.getUsername());
        if (!userByUserName.isPresent()) {
            throw new ServiceException(ErrorCode.build("user", "not-found"));
        }
        UserDto userDto = userByUserName.get();
        if (!userPasswordUtil.match(request.getPassword(), userDto.getPassword())) {
            throw new ServiceException(ErrorCode.build("user", "not-found"));
        }
        AuthResponse authResponse = new AuthResponse(buildToken(userDto));
        return Optional.of(authResponse);
    }


    @Override
    public Optional<String> getAuthenticationUserName(String token) {
        String usernameFromToken = jwtUtil.getUsernameFromToken(token);
        return Optional.of(usernameFromToken);
    }

    private String buildToken(UserDto userDto) {
        Map<String, Object> clams = new HashMap<>();
        clams.put(ClaimName.USERNAME.name(), userDto.getUsername());
        clams.put(ClaimName.ROLES.name(), userDto.getRoles());
        return jwtUtil.build(userDto.getUsername(), clams);
    }
}
