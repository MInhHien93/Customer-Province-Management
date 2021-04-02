package com.codegym.models.service;

import com.codegym.models.entities.Province;

public interface IProvinceService {
    Iterable<Province> findAll();
    Province findById(Long id);
    void save(Province province);
    void delete(Long id);
}
