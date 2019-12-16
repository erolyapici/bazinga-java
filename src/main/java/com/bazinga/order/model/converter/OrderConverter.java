package com.bazinga.order.model.converter;

import com.bazinga.order.model.dto.OrderDto;
import com.bazinga.order.model.dto.OrderEntity;
import com.bazinga.order.model.request.CreateOrderRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Calendar;

public class OrderConverter {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static OrderDto convert(OrderEntity orderEntity) {
        return objectMapper.convertValue(orderEntity, OrderDto.class);
    }

    public static OrderEntity convert(CreateOrderRequest request) {
        OrderEntity entity = new OrderEntity();
        entity.setSubject(request.getSubject());
        entity.setBrandId(request.getBrandId());
        entity.setModelId(request.getModelId());
        entity.setEngineId(request.getEngineId());
        entity.setYear(request.getYear());
        entity.setInsertDate(Calendar.getInstance().getTime());
        return entity;
    }
}
