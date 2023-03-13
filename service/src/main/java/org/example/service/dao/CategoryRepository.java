package org.example.service.dao;

import org.example.service.database.entity.Category;

import javax.persistence.EntityManager;

public class CategoryRepository extends BaseRepository<Integer, Category> {

    public CategoryRepository(Class<Category> clazz, EntityManager entityManager) {
        super(clazz, entityManager);
    }
}
