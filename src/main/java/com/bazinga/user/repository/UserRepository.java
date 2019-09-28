package com.bazinga.user.repository;

import com.bazinga.user.model.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    Optional<List<UserEntity>> getUsers();
}
