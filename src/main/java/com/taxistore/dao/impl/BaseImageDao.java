package com.taxistore.dao.impl;

import com.taxistore.pojo.Result;

import java.util.List;

public interface BaseImageDao<T> {
    Result<T> getEntity(Long id);
    Result<List<T>> getEntities();
    Result<Long> insertEntity(T entity);
    Result<Integer> updateEntity(T entity);
    Result<Integer> deleteEntity(Long id);

    Result<List<T>> getImagesByOwner(Object ownerKey);
    Result<T> getImageByOwnerAndHash(T condition);
    Result<List<String>> getHashCodesByOwner(Object ownerKey);
}
