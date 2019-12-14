package com.bazinga.user.repository.impl;

import com.bazinga.base.repository.GenericDaoFactory;
import com.bazinga.user.model.entity.UserEntity;
import com.bazinga.user.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserRepositoryImpl extends GenericDaoFactory<UserEntity, Integer> implements UserRepository {
    private final Log logger = LogFactory.getLog(getClass());

    public UserRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserEntity> findUserByUserName(String username) {
        return Optional.ofNullable(getSingleResult("username", username));
    }

    @Override
    @Transactional(readOnly = false)
    public Optional<UserEntity> save(UserEntity entity) {
        Integer userId = (Integer) getSession().save(entity);
        return getUserById(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserEntity> getUserById(Integer id) {
        return Optional.ofNullable(getSingleResult("id", id));
    }

    @Override
    public Optional<Boolean> delete(UserEntity userEntity) {
        try {
            getSession().delete(userEntity);
            return Optional.of(Boolean.TRUE);

        } catch (Exception e) {
            logger.error("Could not delete", e);
        }
        return Optional.of(Boolean.FALSE);
    }

    @Override
    public Optional<UserEntity> updateUser(UserEntity userEntity) {
        getSession().saveOrUpdate(userEntity);
        return getUserById(userEntity.getId());
    }
}
