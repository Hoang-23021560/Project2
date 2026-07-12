package com.javaweb.service;

import com.javaweb.model.CustomerRequest;
import com.javaweb.model.CustomerSearchRequest;
import com.javaweb.model.CustomerResponse;

import java.util.List;

public interface CustomerService {
    List<CustomerResponse> findALl(CustomerSearchRequest request);
    void createOrUpdate(CustomerRequest r);
    void deleteCustomer(Long id);
}
