package com.bazinga.service;

import com.bazinga.model.dto.Customer;

import java.util.Optional;

public interface TestService {

    Optional<Customer> getCustomer(Integer id);
}
