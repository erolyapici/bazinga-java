package com.bazinga.base.controller;

import com.bazinga.base.model.dto.BaseDto;
import com.bazinga.base.model.dto.EndpointResponse;
import com.bazinga.base.service.ServiceMethodRunner;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public abstract class BaseController<T> {

    private ServiceMethodRunner methodRunner;
    private T serviceProvider;

    protected <D extends BaseDto> ResponseEntity<EndpointResponse<D>> run(Function<T, Optional<D>> function) {
        return methodRunner.run(serviceProvider, function);
    }

    protected ResponseEntity<EndpointResponse> runVoid(Consumer<T> function) {
        return methodRunner.runVoid(serviceProvider, function);
    }

    protected ServiceMethodRunner getMethodRunner() {
        return methodRunner;
    }

    public void setMethodRunner(ServiceMethodRunner methodRunner) {
        this.methodRunner = methodRunner;
    }

    public void setServiceProvider(T serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

}
