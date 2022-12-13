package com.app.service;

import java.util.List;

public interface BaseService<T, K> {
    T save(T t) throws Exception;

    T update(T t, K id) throws Exception;

    T findById(K id);

    boolean delete(K id);

    List<T> getAll();
}
