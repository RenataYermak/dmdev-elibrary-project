package org.example.service.database.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.service.util.EntityTestUtil.getAuthor;
import static org.example.service.util.EntityTestUtil.getBook;
import static org.example.service.util.EntityTestUtil.getCategory;
import static org.example.service.util.EntityTestUtil.getOrder;
import static org.example.service.util.EntityTestUtil.getUser;

public class OrderIT extends EntityTestBase {
    @Test
    void saveBook() {
        var category = getCategory();
        var author = getAuthor();
        var book = getBook(category, author);
        var user = getUser();
        var order = getOrder(book, user);

        session.save(category);
        session.save(author);
        session.save(book);
        session.save(user);
        session.save(order);

        session.clear();

        assertThat(order.getId()).isNotNull();
    }

    @Test
    void getBookById() {
        var category = getCategory();
        var author = getAuthor();
        var book = getBook(category, author);
        var user = getUser();
        var expectedOrder = getOrder(book, user);

        session.save(category);
        session.save(author);
        session.save(book);
        session.save(user);
        session.save(expectedOrder);

        session.clear();

        var actualOrder = session.get(Order.class, expectedOrder.getId());

        assertThat(expectedOrder).isEqualTo(actualOrder);
    }

    @Test
    void updateBook() {
        var category = getCategory();
        var author = getAuthor();
        var book = getBook(category, author);
        var user = getUser();
        var expectedOrder = getOrder(book, user);

        session.save(category);
        session.save(author);
        session.save(book);
        session.save(user);
        session.save(expectedOrder);

        expectedOrder.setType(OrderType.SEASON_TICKET);
        session.update(expectedOrder);
        session.flush();
        session.clear();

        var actualOrder = session.get(Order.class, expectedOrder.getId());

        assertThat(expectedOrder).isEqualTo(actualOrder);
    }

    @Test
    void deleteBook() {
        var category = getCategory();
        var author = getAuthor();
        var book = getBook(category, author);
        var user = getUser();
        var order = getOrder(book, user);

        session.save(category);
        session.save(author);
        session.save(book);
        session.save(user);
        session.save(order);

        session.delete(order);
        session.flush();
        session.clear();

        var deletedOrder = session.get(Order.class, order.getId());

        assertThat(deletedOrder).isNull();
    }
}
