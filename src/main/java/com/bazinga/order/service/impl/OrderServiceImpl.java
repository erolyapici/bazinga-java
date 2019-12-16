package com.bazinga.order.service.impl;

import com.bazinga.base.exception.ServiceException;
import com.bazinga.base.model.dto.ErrorCode;
import com.bazinga.base.model.dto.ListingDto;
import com.bazinga.order.model.request.CreateOrderRequest;
import com.bazinga.order.model.converter.OrderConverter;
import com.bazinga.order.model.dto.OrderDto;
import com.bazinga.order.model.dto.OrderEntity;
import com.bazinga.order.repository.OrderRepository;
import com.bazinga.order.service.OrderService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    private final Log logger = LogFactory.getLog(getClass());

    private OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Optional<OrderDto> getOrder(Integer orderId) {
        return orderRepository.findById(orderId)
                .map(OrderConverter::convert);
    }

    @Override
    public Optional<ListingDto<OrderDto>> getOrders(Integer page, Integer rows) {
        List<OrderEntity> orders = orderRepository.findOrders(page, rows);
        return Optional.empty();
    }

    @Override
    public Optional<OrderDto> createOrder(CreateOrderRequest request) {
        OrderEntity convert = OrderConverter.convert(request);
        try {
            Integer orderId = orderRepository.save(convert);
            return getOrder(orderId);
        } catch (Exception e) {
            logger.error("Could not create order", e);
            throw new ServiceException(ErrorCode.build("order", "could-not-created"));
        }
    }
}
