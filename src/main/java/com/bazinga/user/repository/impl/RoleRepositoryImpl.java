package com.bazinga.user.repository.impl;

import com.bazinga.base.repository.GenericDaoFactory;
import com.bazinga.user.model.entity.RoleEntity;
import com.bazinga.user.repository.RoleRepository;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public class RoleRepositoryImpl extends GenericDaoFactory<RoleEntity, Integer> implements RoleRepository {
    public RoleRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Optional<List<RoleEntity>> findAll() {
        return super.findAll();
    }
}
