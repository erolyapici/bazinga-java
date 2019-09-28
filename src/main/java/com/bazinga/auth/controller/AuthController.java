package com.bazinga.auth.controller;

import com.bazinga.auth.model.dto.AuthResponse;
import com.bazinga.auth.model.request.AuthRequest;
import com.bazinga.auth.service.AuthService;
import com.bazinga.base.controller.BaseController;
import com.bazinga.base.model.dto.EndpointResponse;
import com.bazinga.base.service.ServiceMethodRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController extends BaseController<AuthService> {


    public AuthController(AuthService authService, ServiceMethodRunner serviceMethodRunner) {
        setMethodRunner(serviceMethodRunner);
        setServiceProvider(authService);
    }

    @PostMapping("/auth")
    public ResponseEntity<EndpointResponse<AuthResponse>> auth(@RequestBody AuthRequest request) {
        return run(authService -> authService.getAuth(request));

    }

}
