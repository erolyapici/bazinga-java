package com.bazinga.controller;

import com.bazinga.base.controller.BaseController;
import com.bazinga.model.dto.Customer;
import com.bazinga.base.model.dto.EndpointResponse;
import com.bazinga.base.service.ServiceMethodRunner;
import com.bazinga.service.TestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController extends BaseController<TestService> {


    public TestController(TestService testService, ServiceMethodRunner serviceMethodRunner) {
        setMethodRunner(serviceMethodRunner);
        setServiceProvider(testService);
    }

    @GetMapping("customer/{customerId}")
    public ResponseEntity<EndpointResponse<Customer>> getCustomer(@PathVariable Integer customerId) {
        return run(service -> service.getCustomer(customerId));

    }
}
