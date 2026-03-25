package com.smartflow.repository;

import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import com.smartflow.model.entity.BaseEntity;

public interface BaseRepository<T extends BaseEntity> extends PanacheRepository<T> {

    default void save(T entity) {

        persist(entity);
    }

    default void save(List<T> entities) {

        persist(entities);
    }

}
