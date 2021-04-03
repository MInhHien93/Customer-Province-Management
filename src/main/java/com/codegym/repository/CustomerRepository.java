package com.codegym.repository;

import com.codegym.models.entities.Customer;
import com.codegym.models.entities.Province;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {
    Iterable<Customer> findAllByProvince(Province province);
}