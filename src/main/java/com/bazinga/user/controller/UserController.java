package com.bazinga.user.controller;

import com.bazinga.base.controller.BaseController;
import com.bazinga.base.model.dto.EndpointResponse;
import com.bazinga.base.model.dto.ListingDto;
import com.bazinga.base.service.ServiceMethodRunner;
import com.bazinga.user.model.dto.UserDto;
import com.bazinga.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController extends BaseController<UserService> {


    public UserController(UserService userService, ServiceMethodRunner serviceMethodRunner) {
        setMethodRunner(serviceMethodRunner);
        setServiceProvider(userService);
    }

    @GetMapping("users")
    public ResponseEntity<EndpointResponse<ListingDto<UserDto>>> getUsers() {
        return run(UserService::getUsers);

    }
}
