package com.bazinga.user.model.converter;

import com.bazinga.base.model.converter.GenericDtoConverter;
import com.bazinga.user.model.UserState;
import com.bazinga.user.model.dto.UserDto;
import com.bazinga.user.model.entity.UserEntity;
import com.bazinga.user.model.request.CreatedUserRequest;
import com.bazinga.user.model.request.UpdateUserRequest;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Component
public class UserConverter implements GenericDtoConverter<UserDto, UserEntity> {
    @Override
    public UserDto convert(UserEntity entity) {
        UserDto userDto = new UserDto();
        userDto.setId(entity.getId());
        userDto.setName(entity.getName());
        userDto.setUsername(entity.getUsername());
        userDto.setSurname(entity.getSurname());
        userDto.setState(entity.getState());
        userDto.setPassword(entity.getPassword());
        userDto.setInsertDate(entity.getInsertDate());
        userDto.setUpdateDate(entity.getUpdateDate());
        return userDto;
    }

    public UserEntity convert(CreatedUserRequest request) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(request.getUsername());
        userEntity.setName(request.getName());
        userEntity.setSurname(request.getSurname());
        userEntity.setPassword(request.getPassword());
        userEntity.setInsertDate(Calendar.getInstance().getTime());
        userEntity.setState(UserState.WAITING_APPROVE.getState());
        return userEntity;
    }

    public UserEntity convert(UserEntity userEntity, UpdateUserRequest request) {
        userEntity.setUsername(request.getUsername());
        userEntity.setName(request.getName());
        userEntity.setSurname(request.getSurname());
        userEntity.setPassword(request.getPassword());
        userEntity.setUpdateDate(Calendar.getInstance().getTime());
        return userEntity;
    }
}
