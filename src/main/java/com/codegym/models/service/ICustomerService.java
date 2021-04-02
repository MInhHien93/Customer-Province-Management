package com.codegym.models.service;

import com.codegym.models.entities.Customer;

public interface ICustomerService {
    Iterable<Customer> findAll();
    Customer findById(Long id);
    void save(Customer customer);
    void delete(Long id);
}
