package com.bazinga.service.impl;

import com.bazinga.base.exception.ServiceException;
import com.bazinga.base.model.dto.ErrorCode;
import com.bazinga.model.dto.Customer;
import com.bazinga.service.TestService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TestServiceImpl implements TestService {
    @Override
    public Optional<Customer> getCustomer(Integer id) {
        throw new ServiceException(ErrorCode.build("system"));/*
        Customer customer = new Customer();
        customer.setName("erol");
        customer.setLastName("yapıcı");
        return Optional.of(customer);*/
    }
}
