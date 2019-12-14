package com.bazinga.user.service;

import com.bazinga.base.model.dto.ListingDto;
import com.bazinga.user.model.dto.UserDto;
import com.bazinga.user.model.request.CreatedUserRequest;
import com.bazinga.user.model.request.UpdateUserRequest;

import java.util.Optional;

public interface UserService {

    Optional<ListingDto<UserDto>> getUsers();

    Optional<UserDto> createUser(CreatedUserRequest request);

    Optional<UserDto> getUserByUserName(String username);


    Optional<UserDto> getUser(Integer userId);

    void deleteUser(Integer userId);

    Optional<UserDto> updateUser(Integer userId, UpdateUserRequest request);
}
