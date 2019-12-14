package com.bazinga.user.service.impl;

import com.bazinga.user.model.entity.RoleEntity;
import com.bazinga.user.repository.RoleRepository;
import com.bazinga.user.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleRepository repository;

    public RoleServiceImpl(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Map<Integer, String>> getRoles() {
        Map<Integer, String> roles = new HashMap<>();
        Optional<List<RoleEntity>> all = repository.findAll();
        all.ifPresent(roleEntities -> roleEntities.forEach(roleEntity ->
                roles.put(roleEntity.getRoleId(), roleEntity.getRole())));
        return Optional.of(roles);
    }
}
