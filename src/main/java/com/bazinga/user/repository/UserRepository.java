package com.bazinga.user.repository;

import com.bazinga.user.model.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    Optional<List<UserEntity>> findAll();

    Optional<UserEntity> findUserByUserName(String username);

    Optional<UserEntity> save(UserEntity entity);

    Optional<UserEntity> getUserById(Integer id);

    Optional<Boolean> delete(UserEntity userEntity);

    Optional<UserEntity> updateUser(UserEntity userEntity);
}
