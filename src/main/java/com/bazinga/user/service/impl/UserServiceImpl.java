package com.bazinga.user.service.impl;

import com.bazinga.base.exception.ServiceException;
import com.bazinga.base.model.dto.ErrorCode;
import com.bazinga.base.model.dto.ListingDto;
import com.bazinga.user.model.converter.UserConverter;
import com.bazinga.user.model.dto.UserDto;
import com.bazinga.user.model.entity.UserEntity;
import com.bazinga.user.model.request.CreatedUserRequest;
import com.bazinga.user.model.request.UpdateUserRequest;
import com.bazinga.user.repository.UserRepository;
import com.bazinga.user.service.RoleService;
import com.bazinga.user.service.UserService;
import com.bazinga.user.util.UserPasswordUtil;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private UserConverter userConverter;
    private RoleService roleService;
    private UserPasswordUtil userPasswordUtil;

    public UserServiceImpl(UserRepository userRepository, UserConverter userConverter, RoleService roleService, UserPasswordUtil userPasswordUtil) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
        this.roleService = roleService;
        this.userPasswordUtil = userPasswordUtil;
    }

    @Override
    public Optional<ListingDto<UserDto>> getUsers() {
        Optional<List<UserEntity>> users = userRepository.findAll();
        if (users.isPresent() && !CollectionUtils.isEmpty(users.get())) {
            List<UserDto> dtos = users.get().stream().map(userConverter::convert).collect(Collectors.toList());
            ListingDto<UserDto> response = new ListingDto<>();
            response.setTypes(dtos);
            response.setTotalCount(dtos.size());
            return Optional.of(response);
        }
        throw new ServiceException(ErrorCode.build("user", "not-found"));
    }

    @Override
    public Optional<UserDto> createUser(CreatedUserRequest request) {
        Optional<UserDto> userByUserName = getUserByUserName(request.getUsername());
        if (userByUserName.isPresent()) {
            throw new ServiceException(ErrorCode.build("user", "could-not-created"));
        }
        UserEntity entity = userConverter.convert(request);
        entity.setPassword(userPasswordUtil.encode(request.getPassword()).get());
        Optional<UserEntity> userEntity = userRepository.save(entity);
        if (userEntity.isPresent()) {
            return Optional.of(userConverter.convert(userEntity.get()));
        }
        throw new ServiceException(ErrorCode.build("user", "could-not-created"));

    }

    @Override
    public Optional<UserDto> getUserByUserName(String username) {
        Optional<UserEntity> userByUserName = userRepository.findUserByUserName(username);

        if (userByUserName.isPresent()) {
            UserEntity userEntity = userByUserName.get();
            UserDto userDto = userConverter.convert(userEntity);
            if (!CollectionUtils.isEmpty(userEntity.getRoles())) {
                Optional<Map<Integer, String>> roles = roleService.getRoles();
                if (roles.isPresent()) {
                    List<String> userRoles = userEntity.getRoles().stream()
                            .filter(userRoleEntity -> roles.get()
                                    .containsKey(userRoleEntity.getRoleId()))
                            .map(userRoleEntity -> roles.get().get(userRoleEntity.getRoleId())).collect(Collectors.toList());
                    userDto.setRoles(userRoles);
                }
            }
            return Optional.of(userDto);
        }
        throw new ServiceException(ErrorCode.build("user", "not-found"));
    }

    @Override
    public Optional<UserDto> getUser(Integer userId) {
        Optional<UserEntity> userById = userRepository.getUserById(userId);
        if (userById.isPresent()) {
            return Optional.of(userConverter.convert(userById.get()));
        }
        throw new ServiceException(ErrorCode.build("user", "not-found"));
    }

    @Override
    public void deleteUser(Integer userId) {
        Optional<UserEntity> userById = userRepository.getUserById(userId);
        if (!userById.isPresent()) {
            throw new ServiceException(ErrorCode.build("user", "not-found"));
        }
        Optional<Boolean> delete = userRepository.delete(userById.get());
        if (!delete.isPresent()) {
            throw new ServiceException(ErrorCode.build("user", "could-not-deleted"));
        }
    }

    @Override
    public Optional<UserDto> updateUser(Integer userId, UpdateUserRequest request) {
        Optional<UserEntity> userById = userRepository.getUserById(userId);
        if (!userById.isPresent()) {
            throw new ServiceException(ErrorCode.build("user", "not-found"));
        }
        UserEntity userEntity = userById.get();
        userConverter.convert(userEntity, request);
        Optional<UserEntity> updateUser = userRepository.updateUser(userEntity);
        if (updateUser.isPresent()) {
            return Optional.of(userConverter.convert(updateUser.get()));
        }
        throw new ServiceException(ErrorCode.build("user", "could-not-updated"));
    }

}
