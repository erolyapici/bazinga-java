package com.bazinga.user.service;

import com.bazinga.base.model.dto.ListingDto;
import com.bazinga.user.model.dto.UserDto;

import java.util.Optional;

public interface UserService {

    Optional<ListingDto<UserDto>> getUsers();
}
