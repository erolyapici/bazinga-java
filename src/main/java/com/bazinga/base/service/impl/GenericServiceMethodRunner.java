package com.bazinga.base.service.impl;


import com.bazinga.base.model.dto.BaseDto;
import com.bazinga.base.model.dto.EndpointResponse;
import com.bazinga.base.service.ServiceMethodRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

@Component
public class GenericServiceMethodRunner<T, D extends BaseDto> implements ServiceMethodRunner<T, D> {


    @Override
    public ResponseEntity<EndpointResponse<D>> run(T arg, Function<T, Optional<D>> function) {
        Optional<D> methodResult = function.apply(arg);
        EndpointResponse<D> endpointResponse = buildEndpointResponse(methodResult);
        return new ResponseEntity<>(endpointResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<EndpointResponse> runVoid(T arg, Consumer<T> function) {
        function.accept(arg);
        EndpointResponse endpointResponse = new EndpointResponse();
        return new ResponseEntity<>(endpointResponse, HttpStatus.OK);
    }

    private EndpointResponse<D> buildEndpointResponse(Optional<D> methodResult) {
        EndpointResponse<D> endpointResponse = new EndpointResponse<>();
        methodResult.ifPresent(endpointResponse::setData);
        return endpointResponse;
    }

}
