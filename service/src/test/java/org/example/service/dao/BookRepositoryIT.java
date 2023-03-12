package org.example.service.dao;

import org.example.service.dto.BookFilter;
import org.example.service.integration.IntegrationTestBase;
import org.example.service.util.EntityTestUtil;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BookRepositoryIT extends IntegrationTestBase {

    @Test
    void findById() {
        var bookRepository = new BookRepository(session);

        var actualBook = bookRepository.findById(1L);

        assertThat(actualBook).isPresent();
        assertThat(actualBook.get().getTitle()).isEqualTo("Death on the Nile");
    }

    @Test
    void findAll() {
        var bookRepository = new BookRepository(session);

        var books = bookRepository.findAll();

        assertNotNull(books);
        assertThat(books).hasSize(4);
    }

    @Test
    void save() {
        var category = EntityTestUtil.getCategory();
        var author = EntityTestUtil.getAuthor();
        var book = EntityTestUtil.getBook(category, author);

        var categoryRepository = new CategoryRepository(session);
        var authorRepository = new AuthorRepository(session);
        var bookRepository = new BookRepository(session);

        categoryRepository.save(category);
        authorRepository.save(author);

        var actualBook = bookRepository.save(book);

        assertThat(actualBook).isNotNull();
    }

    @Test
    void deleteExistingBook() {
        var bookRepository = new BookRepository(session);

        bookRepository.delete(1L);

        assertThat(bookRepository.findById(1L)).isEmpty();
    }

    @Test
    void deleteNotExistingBook() {
        var bookRepository = new BookRepository(session);

        assertThrows(IllegalArgumentException.class, () -> bookRepository.delete(100500900L));
    }

    @Test
    void update() {
        var bookRepository = new BookRepository(session);

        var expectedBook = bookRepository.findById(1L).get();
        expectedBook.setTitle("New Title");
        bookRepository.update(expectedBook);

        var actualBook = bookRepository.findById(1L);

        assertThat(actualBook).isPresent();
        assertThat(actualBook.get().getTitle()).isEqualTo("New Title");
    }

    @Test
    void findByFilterQueryDslWithAllParameters() {
        var bookRepository = new BookRepository(session);

        var filter = BookFilter.builder()
                .publishYear(1937)
                .category("Detective")
                .author("Agatha Christie")
                .build();

        var books = bookRepository.findByFilterQueryDsl(filter);

        assertNotNull(books);
        assertThat(books).hasSize(1);
        assertThat(books.get(0).getId()).isEqualTo(1);
    }

    @Test
    void findByFilterQueryDslWithTwoParameters() {
        var bookRepository = new BookRepository(session);

        var filter = BookFilter.builder()
                .publishYear(1937)
                .category("Detective")
                .build();

        var books = bookRepository.findByFilterQueryDsl(filter);

        assertNotNull(books);
        assertThat(books).hasSize(2);
        assertThat(books.get(0).getId()).isEqualTo(1);
        assertThat(books.get(1).getId()).isEqualTo(4);
    }

    @Test
    void findByFilterQueryDslWithoutParameters() {
        var bookRepository = new BookRepository(session);

        var filter = BookFilter.builder()
                .build();

        var books = bookRepository.findByFilterQueryDsl(filter);

        assertThat(books).hasSize(bookRepository.findAll().size());
    }
}
