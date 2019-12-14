package com.bazinga.auth.service.impl;

import com.bazinga.auth.model.dto.AuthResponse;
import com.bazinga.auth.model.request.AuthRequest;
import com.bazinga.auth.model.util.JwtUtil;
import com.bazinga.base.exception.ServiceException;
import com.bazinga.user.model.dto.UserDto;
import com.bazinga.user.service.UserService;
import com.bazinga.user.util.UserPasswordUtil;
import io.jsonwebtoken.lang.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class AuthServiceImplTest {
    private static final String USERNAME = "username";
    private static final String TOKEN = "teoken";
    @Mock
    private JwtUtil jwtUtil;
    @Mock
    private UserService userService;
    @Mock
    private UserPasswordUtil userPasswordUtil;

    private AuthServiceImpl authService;

    private UserDto userDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        PodamFactory factory = new PodamFactoryImpl();
        userDto = factory.manufacturePojo(UserDto.class);
        authService = new AuthServiceImpl(jwtUtil, userService, userPasswordUtil);
    }

    @Test
    void shouldReturnToken() {
        when(userService.getUserByUserName(USERNAME)).thenReturn(Optional.of(userDto));
        when(userPasswordUtil.match(any(), any())).thenReturn(Boolean.TRUE);
        when(jwtUtil.build(any(), any())).thenReturn(TOKEN);
        AuthRequest request = new AuthRequest();
        request.setUsername(USERNAME);
        request.setPassword("test");
        Optional<AuthResponse> auth = authService.getAuth(request);
        Assert.notNull(auth);
        Assert.isTrue(auth.isPresent());
        Assert.notNull(auth.get().getToken());
    }

    @Test
    void catchUserNotFoundException() {
        when(userService.getUserByUserName(USERNAME)).thenReturn(Optional.empty());
        AuthRequest request = new AuthRequest();
        request.setUsername(USERNAME);
        request.setPassword("test");
        Assertions.assertThrows(ServiceException.class, () -> authService.getAuth(request));
    }

    @Test
    void catchUserPasswordException() {
        when(userService.getUserByUserName(USERNAME)).thenReturn(Optional.of(userDto));
        when(userPasswordUtil.match(any(), any())).thenReturn(Boolean.FALSE);
        AuthRequest request = new AuthRequest();
        request.setUsername(USERNAME);
        request.setPassword("test");
        Assertions.assertThrows(ServiceException.class, () -> authService.getAuth(request));
    }

    @Test
    void shouldReturnUsername() {
        when(jwtUtil.getUsernameFromToken(TOKEN)).thenReturn(USERNAME);
        Optional<String> auth = authService.getAuthenticationUserName(TOKEN);
        Assert.notNull(auth);
        Assert.isTrue(auth.isPresent());
        Assertions.assertEquals(USERNAME, auth.get());

    }
}
