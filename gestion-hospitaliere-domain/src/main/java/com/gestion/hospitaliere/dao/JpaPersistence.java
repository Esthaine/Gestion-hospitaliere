package com.gestion.hospitaliere.dao;

import java.util.List;

public interface JpaPersistence <T>{
    List<T> findAll();
    T findById(Long id);
    T deleteById(Long id);
    T delete(T t);
    T save(T t);
}
