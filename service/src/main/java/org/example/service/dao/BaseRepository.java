package org.example.service.dao;

import lombok.RequiredArgsConstructor;
import org.example.service.database.entity.BaseEntity;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class BaseRepository<T extends Serializable, E extends BaseEntity<T>> implements Repository<T, E> {

    private final Class<E> clazz;
    protected final EntityManager entityManager;

    @Override
    public Optional<E> findById(T id) {
        return Optional.ofNullable(entityManager.find(clazz, id));
    }

    @Override
    public List<E> findAll() {
        var criteria = entityManager.getCriteriaBuilder().createQuery(clazz);
        criteria.from(clazz);
        return entityManager.createQuery(criteria)
                .getResultList();
    }

    @Override
    public E save(E entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public void delete(T id) {
        entityManager.remove(entityManager.find(clazz, id));
        entityManager.flush();
    }

    @Override
    public void update(E entity) {
        entityManager.merge(entity);
    }
}
