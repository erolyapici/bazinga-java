package com.bazinga.order.repository;

import com.bazinga.order.model.dto.OrderEntity;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    Optional<OrderEntity> findById(Integer orderId);

    Integer save(OrderEntity request);

    List<OrderEntity> findOrders(Integer page, Integer rows);
}
