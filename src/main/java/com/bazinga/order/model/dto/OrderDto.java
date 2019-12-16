package com.bazinga.order.model.dto;

import com.bazinga.base.model.dto.BaseDto;
import com.bazinga.order.model.OrderState;
import lombok.Data;

import java.util.Date;

@Data
public class OrderDto extends BaseDto {
    private Integer orderId;
    private String subject;
    private Integer brandId;
    private Integer modelId;
    private Integer year;
    private Integer engineId;
    private OrderState state;
    private Integer insertUser;
    private Integer updateUser;
    private Date insertDate;
    private Date updateDate;
}
