package org.example.service.database.entity;

import org.example.service.integration.IntegrationTestBase;
import org.example.service.util.EntityTestUtil;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BookIT extends IntegrationTestBase {

    @Test
    void saveBook() {
        var category = EntityTestUtil.getCategory();
        var author = EntityTestUtil.getAuthor();
        var book = EntityTestUtil.getBook(category, author);

        session.save(category);
        session.save(author);
        session.save(book);

        session.clear();

        assertThat(book.getId()).isNotNull();
    }

    @Test
    void getBookById() {
        var category = EntityTestUtil.getCategory();
        var author = EntityTestUtil.getAuthor();
        var expectedBook = EntityTestUtil.getBook(category, author);

        session.save(category);
        session.save(author);
        session.save(expectedBook);

        session.clear();

        var actualBook = session.get(Book.class, expectedBook.getId());

        assertThat(expectedBook).isEqualTo(actualBook);
    }

    @Test
    void updateBook() {
        var category = EntityTestUtil.getCategory();
        var author = EntityTestUtil.getAuthor();
        var expectedBook = EntityTestUtil.getBook(category, author);

        session.save(category);
        session.save(author);
        session.save(expectedBook);

        expectedBook.setTitle("New Title");
        session.update(expectedBook);
        session.flush();
        session.clear();

        var actualBook = session.get(Book.class, expectedBook.getId());

        assertThat(expectedBook.getTitle()).isEqualTo(actualBook.getTitle());
    }

    @Test
    void deleteBook() {
        var category = EntityTestUtil.getCategory();
        var author = EntityTestUtil.getAuthor();
        var book = EntityTestUtil.getBook(category, author);

        session.save(category);
        session.save(author);
        session.save(book);

        session.delete(book);
        session.flush();
        session.clear();

        var deletedBook = session.get(Book.class, book.getId());

        assertThat(deletedBook).isNull();
    }
}
