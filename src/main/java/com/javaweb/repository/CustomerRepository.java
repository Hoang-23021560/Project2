package com.javaweb.repository;

import com.javaweb.model.CustomerSearchRequest;
import com.javaweb.repository.entity.CustomerEntity;
import com.javaweb.repository.entity.UserEntity;

import java.util.List;

public interface CustomerRepository {
    List<CustomerEntity> findAll(CustomerSearchRequest request);
    void createCustomer(CustomerEntity customerEntity);
    void updateCustomer(CustomerEntity customerEntity);
    void deleteCustomer(Long id);
    CustomerEntity findById(Long id);
    List<UserEntity> findById(List<Long> ids);
}
