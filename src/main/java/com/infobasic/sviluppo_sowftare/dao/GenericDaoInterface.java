package com.infobasic.sviluppo_sowftare.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDaoInterface<T, ID> {

    T findById(ID id);

    List<T> findAll();

    T create(T entity);

    T update(T entity);

    void deleteById(ID id);

    long count();
}