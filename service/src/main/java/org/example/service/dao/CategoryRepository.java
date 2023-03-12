package org.example.service.dao;

import org.example.service.database.entity.Category;

import javax.persistence.EntityManager;

public class CategoryRepository extends BaseRepository<Integer, Category> {

    public CategoryRepository(EntityManager entityManager) {
        super(Category.class, entityManager);
    }
}
