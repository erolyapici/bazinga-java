package com.bazinga.user.controller;

import com.bazinga.base.controller.BaseController;
import com.bazinga.base.model.dto.EndpointResponse;
import com.bazinga.base.model.dto.ListingDto;
import com.bazinga.base.service.ServiceMethodRunner;
import com.bazinga.user.model.dto.UserDto;
import com.bazinga.user.model.request.CreatedUserRequest;
import com.bazinga.user.model.request.UpdateUserRequest;
import com.bazinga.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@PreAuthorize("hasRole('ADMIN')")
public class UserController extends BaseController<UserService> {


    public UserController(UserService userService, ServiceMethodRunner serviceMethodRunner) {
        setMethodRunner(serviceMethodRunner);
        setServiceProvider(userService);
    }

    @GetMapping("users")
    public ResponseEntity<EndpointResponse<ListingDto<UserDto>>> getUsers() {
        return run(UserService::getUsers);
    }

    @PostMapping("user")
    public ResponseEntity<EndpointResponse<UserDto>> createUser(@RequestBody CreatedUserRequest request) {
        return run(service -> service.createUser(request));
    }

    @PutMapping("user/{userId}")
    public ResponseEntity<EndpointResponse<UserDto>> updateUser(@PathVariable("userId") Integer userId, @RequestBody UpdateUserRequest request) {
        return run(service -> service.updateUser(userId, request));
    }

    @GetMapping("user/{userId}")
    public ResponseEntity<EndpointResponse<UserDto>> getUser(@PathVariable("userId") Integer userId) {
        return run(service -> service.getUser(userId));
    }

    @DeleteMapping("user/{userId}")
    public void deleteUser(@PathVariable("userId") Integer userId) {
        runVoid(service -> service.deleteUser(userId));
    }
}
