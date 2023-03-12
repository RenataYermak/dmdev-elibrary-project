package org.example.service.dao;

import org.example.service.database.entity.Author;

import javax.persistence.EntityManager;

public class AuthorRepository extends BaseRepository<Long, Author> {

    public AuthorRepository(EntityManager entityManager) {
        super(Author.class, entityManager);
    }
}
