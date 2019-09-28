package com.bazinga.user.model.converter;

import com.bazinga.base.model.converter.GenericDtoConverter;
import com.bazinga.user.model.dto.UserDto;
import com.bazinga.user.model.entity.UserEntity;
import org.springframework.stereotype.Component;

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
}
