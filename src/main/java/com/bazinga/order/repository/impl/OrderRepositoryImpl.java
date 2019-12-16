package com.bazinga.order.repository.impl;

import com.bazinga.base.repository.GenericDaoFactory;
import com.bazinga.order.model.dto.OrderEntity;
import com.bazinga.order.repository.OrderRepository;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public class OrderRepositoryImpl extends GenericDaoFactory<OrderEntity, Integer> implements OrderRepository {
    public OrderRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Optional<OrderEntity> findById(Integer orderId) {
        return Optional.of(getSingleResult("orderId", orderId));
    }

    @Override
    @Transactional(readOnly = false)
    public Integer save(OrderEntity request) {
        return (Integer) getSession().save(request);
    }

    @Override
    public List<OrderEntity> findOrders(Integer page, Integer rows) {
        return null;
    }
}
