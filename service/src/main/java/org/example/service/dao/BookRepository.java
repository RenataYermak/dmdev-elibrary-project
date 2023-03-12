package org.example.service.dao;

import org.example.service.database.entity.Book;

import javax.persistence.EntityManager;

public class BookRepository extends BaseRepository<Long, Book> {

    public BookRepository(EntityManager entityManager) {
        super(Book.class, entityManager);
    }
}
