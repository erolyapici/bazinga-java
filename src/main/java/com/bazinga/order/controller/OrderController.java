package com.bazinga.order.controller;

import com.bazinga.base.controller.BaseController;
import com.bazinga.base.model.dto.EndpointResponse;
import com.bazinga.base.model.dto.ListingDto;
import com.bazinga.base.service.ServiceMethodRunner;
import com.bazinga.order.model.request.CreateOrderRequest;
import com.bazinga.order.model.dto.OrderDto;
import com.bazinga.order.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@PreAuthorize("hasRole('ADMIN')")
public class OrderController extends BaseController<OrderService> {


    public OrderController(OrderService orderService, ServiceMethodRunner serviceMethodRunner) {
        setMethodRunner(serviceMethodRunner);
        setServiceProvider(orderService);
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<EndpointResponse<OrderDto>> getOrder(@PathVariable("orderId") Integer orderId) {
        return run(service -> service.getOrder(orderId));
    }

    @PostMapping("/order")
    public ResponseEntity<EndpointResponse<OrderDto>> createOrder(@RequestBody CreateOrderRequest request) {
        return run(service -> service.createOrder(request));
    }

    @GetMapping("/order")
    public ResponseEntity<EndpointResponse<ListingDto<OrderDto>>> getOrders(@ModelAttribute("page") Integer page, @ModelAttribute("rows") Integer rows) {
        return run(service -> service.getOrders(page, rows));
    }
}
