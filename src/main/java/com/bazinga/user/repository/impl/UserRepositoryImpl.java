package com.bazinga.user.repository.impl;

import com.bazinga.base.repository.GenericDaoFactory;
import com.bazinga.user.model.entity.UserEntity;
import com.bazinga.user.repository.UserRepository;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserRepositoryImpl extends GenericDaoFactory<UserEntity, Integer> implements UserRepository {

    public UserRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Optional<List<UserEntity>> getUsers() {
        return Optional.ofNullable(findAll());
    }

}
