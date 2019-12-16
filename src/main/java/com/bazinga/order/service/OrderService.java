package com.bazinga.order.service;


import com.bazinga.base.model.dto.ListingDto;
import com.bazinga.order.model.request.CreateOrderRequest;
import com.bazinga.order.model.dto.OrderDto;

import java.util.Optional;

public interface OrderService {

    Optional<OrderDto> getOrder(Integer orderId);

    Optional<ListingDto<OrderDto>> getOrders(Integer page, Integer rows);

    Optional<OrderDto> createOrder(CreateOrderRequest request);
}
