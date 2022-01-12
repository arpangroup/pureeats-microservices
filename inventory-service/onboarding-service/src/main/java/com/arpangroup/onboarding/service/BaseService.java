package com.arpangroup.onboarding.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BaseService<T> {
    List<T> list();
    Page<T> findAll(Pageable pageable);
    Page<T> findAll(Long id, Pageable pageable);
    T findOne(Long id);
    T add(T type);
    void delete(Long id);
}
