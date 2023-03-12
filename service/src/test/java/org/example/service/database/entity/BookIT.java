package org.example.service.database.entity;

import org.assertj.core.api.Assertions;
import org.example.service.integration.IntegrationTestBase;
import org.example.service.util.EntityTestUtil;
import org.junit.jupiter.api.Test;

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

        Assertions.assertThat(book.getId()).isNotNull();
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

        Assertions.assertThat(expectedBook).isEqualTo(actualBook);
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

        Assertions.assertThat(expectedBook.getTitle()).isEqualTo(actualBook.getTitle());
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

        Assertions.assertThat(deletedBook).isNull();
    }
}
