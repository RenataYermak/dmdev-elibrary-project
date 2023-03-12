package org.example.service.dao;

import org.example.service.database.entity.Book;
import org.example.service.integration.IntegrationTestBase;
import org.example.service.util.EntityTestUtil;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class BookRepositoryIT extends IntegrationTestBase {

    @Test
    void findById() {
        var bookRepository = new BookRepository(session);

        Optional<Book> actualBook = bookRepository.findById(1L);

        assertThat(actualBook).isPresent();
        assertThat(actualBook.get().getTitle()).isEqualTo("Death on the Nile");
    }

    @Test
    void findAll() {
        var bookRepository = new BookRepository(session);

        List<Book> books = bookRepository.findAll();

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
    void delete() {
        var bookRepository = new BookRepository(session);

        bookRepository.delete(1L);

        assertThat(bookRepository.findById(1L)).isEmpty();
    }

    @Test
    void update() {
        var bookRepository = new BookRepository(session);

        var expectedBook = bookRepository.findById(1L).get();
        expectedBook.setTitle("New Title");
        bookRepository.update(expectedBook);

        Optional<Book> actualBook = bookRepository.findById(1L);

        assertThat(actualBook).isPresent();
        assertThat(actualBook.get().getTitle()).isEqualTo("New Title");
    }
}
