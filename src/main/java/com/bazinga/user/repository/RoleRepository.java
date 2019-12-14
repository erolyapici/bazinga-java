package com.bazinga.user.repository;

import com.bazinga.user.model.entity.RoleEntity;

import java.util.List;
import java.util.Optional;

public interface RoleRepository {

    Optional<List<RoleEntity>> findAll();
}
