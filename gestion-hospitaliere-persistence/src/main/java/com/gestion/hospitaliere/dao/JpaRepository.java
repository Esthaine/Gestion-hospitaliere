package com.gestion.hospitaliere.dao;

import java.util.List;

public interface JpaRepository <T>{

    T save(T t);
    List<T> findAll();
    T findById(Long id);
    T deleteById(Long id);
    List<T> saveAll(T... t);
    List<T> deleteMany(Long ...ids);
    List<T> deleteMany(T... ts);
    long count();
}
