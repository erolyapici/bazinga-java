package com.bazinga.order.model.request;

import com.bazinga.base.model.request.BaseRequest;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CreateOrderRequest extends BaseRequest {
    @NotNull
    private String subject;
    @NotNull
    private Integer brandId;
    @NotNull
    private Integer modelId;
    @NotNull
    private Integer year;
    @NotNull
    private Integer engineId;
}
