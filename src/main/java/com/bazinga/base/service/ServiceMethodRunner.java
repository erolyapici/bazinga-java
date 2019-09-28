package com.bazinga.base.service;

import com.bazinga.base.model.dto.BaseDto;
import com.bazinga.base.model.dto.EndpointResponse;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public interface ServiceMethodRunner<T, D extends BaseDto> {

    ResponseEntity<EndpointResponse<D>> run(T arg, Function<T, Optional<D>> function);

    ResponseEntity<EndpointResponse> runVoid(T arg, Consumer<T> consumer);

}
