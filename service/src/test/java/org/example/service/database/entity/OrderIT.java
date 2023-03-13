package org.example.service.database.entity;

import org.example.service.integration.IntegrationTestBase;
import org.example.service.util.EntityTestUtil;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderIT extends IntegrationTestBase {
    @Test
    void saveBook() {
        var category = EntityTestUtil.getCategory();
        var author = EntityTestUtil.getAuthor();
        var book = EntityTestUtil.getBook(category, author);
        var user = EntityTestUtil.getUser();
        var order = EntityTestUtil.getOrder(book, user);

        session.save(category);
        session.save(author);
        session.save(book);
        session.save(user);
        session.save(order);

        assertThat(order.getId()).isNotNull();
    }

    @Test
    void getBookById() {
        var category = EntityTestUtil.getCategory();
        var author = EntityTestUtil.getAuthor();
        var book = EntityTestUtil.getBook(category, author);
        var user = EntityTestUtil.getUser();
        var expectedOrder = EntityTestUtil.getOrder(book, user);

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
        var category = EntityTestUtil.getCategory();
        var author = EntityTestUtil.getAuthor();
        var book = EntityTestUtil.getBook(category, author);
        var user = EntityTestUtil.getUser();
        var expectedOrder = EntityTestUtil.getOrder(book, user);

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
        var category = EntityTestUtil.getCategory();
        var author = EntityTestUtil.getAuthor();
        var book = EntityTestUtil.getBook(category, author);
        var user = EntityTestUtil.getUser();
        var order = EntityTestUtil.getOrder(book, user);

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
