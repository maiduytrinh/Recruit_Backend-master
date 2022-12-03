package com.app.service;

public interface BaseService<T, K> {
    T save(T t) throws Exception;

    T update(T t, K id) throws Exception;

    T findById(K id);

    boolean delete(K id);
}
