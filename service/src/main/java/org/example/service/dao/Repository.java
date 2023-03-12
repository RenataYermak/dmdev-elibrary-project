package org.example.service.dao;

import org.example.service.database.entity.BaseEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface Repository<T extends Serializable, E extends BaseEntity> {

    E save(E entity);

    void delete(T id);

    void update(E entity);

    Optional<E> findById(T id);

    List<E> findAll();

}
