package com.bazinga.user.service.impl;

import com.bazinga.base.exception.ServiceException;
import com.bazinga.base.model.dto.ErrorCode;
import com.bazinga.base.model.dto.ListingDto;
import com.bazinga.user.model.converter.UserConverter;
import com.bazinga.user.model.dto.UserDto;
import com.bazinga.user.model.entity.UserEntity;
import com.bazinga.user.repository.UserRepository;
import com.bazinga.user.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private UserConverter userConverter;

    public UserServiceImpl(UserRepository userRepository, UserConverter userConverter) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
    }

    @Override
    public Optional<ListingDto<UserDto>> getUsers() {
        Optional<List<UserEntity>> users = userRepository.getUsers();
        if (users.isPresent() && !CollectionUtils.isEmpty(users.get())) {
            List<UserDto> dtos = users.get().stream().map(userConverter::convert).collect(Collectors.toList());
            ListingDto<UserDto> response = new ListingDto<>();
            response.setTypes(dtos);
            response.setTotalCount(dtos.size());
            return Optional.of(response);
        }
        throw new ServiceException(ErrorCode.build("user", "not-found"));
    }
}
