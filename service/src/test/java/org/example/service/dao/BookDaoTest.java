package org.example.service.dao;

import org.example.service.database.entity.Book;
import org.example.service.dto.BookFilter;
import org.example.service.integration.IntegrationTestBase;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BookDaoTest extends IntegrationTestBase {

    private final BookDao bookDao = BookDao.getInstance();


    @Test
    void findAll() {
        List<Book> results = bookDao.findAll(session);

        assertThat(results).hasSize(4);

    }

    @Test
    void findById() {
        List<Book> results = bookDao.findAll(session);
        assertThat(results).hasSize(4);

        List<Long> book = results.stream().map(Book::getId).toList();
        assertThat(book).containsExactlyInAnyOrder(1L, 2L, 3L, 4L);
    }

    @Test
    void findAllByFilterQueryDsl() {
        BookFilter filter = BookFilter.builder()
                .publishYear(1937)
                .category("Detective")
                .author("Agatha Christie")
                .build();
        List<Book> result = bookDao.findAllByFilterQueryDsl(session, filter);
        assertThat(result).hasSize(1);
    }

    @Test
    void findAllByFilterCriteria() {
        BookFilter filter = BookFilter.builder()
                .publishYear(1937)
                .category("Detective")
                .author("Agatha Christie")
                .build();
        List<Book> result = bookDao.findAllByFilterCriteria(session, filter);
        assertThat(result).hasSize(1);
    }
}