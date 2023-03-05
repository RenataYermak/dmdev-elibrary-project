package org.example.service.dao;

import org.example.service.database.entity.Book;
import org.example.service.dto.BookFilter;
import org.example.service.integration.IntegrationTestBase;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BookDaoIT extends IntegrationTestBase {

    private final BookDao bookDao = BookDao.getInstance();

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
