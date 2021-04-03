package com.codegym.models.service;

import com.codegym.models.entities.Customer;
import com.codegym.models.entities.Province;

public interface ICustomerService {
    Iterable<Customer> findAll();
    Customer findById(Long id);
    void save(Customer customer);
    void delete(Long id);
    Iterable<Customer> findAllByProvince(Province province);
}
