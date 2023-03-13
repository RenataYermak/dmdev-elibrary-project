package org.example.service.dao;

import org.example.service.database.entity.Order;
import org.example.service.database.entity.OrderStatus;
import org.example.service.database.entity.OrderType;
import org.example.service.dto.OrderFilter;
import org.example.service.integration.IntegrationTestBase;
import org.example.service.util.EntityTestUtil;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class OrderRepositoryIT extends IntegrationTestBase {

    OrderRepository orderRepository = new OrderRepository(Order.class, session);

    @Test
    void findById() {
        var actualOrder = orderRepository.findById(1L);
        session.clear();

        assertThat(actualOrder).isPresent();
        assertThat(actualOrder.get().getType()).isEqualTo(OrderType.SEASON_TICKET);
    }

    @Test
    void findAll() {
        var orders = orderRepository.findAll();
        session.clear();

        assertNotNull(orders);
        assertThat(orders).hasSize(5);
    }

    @Test
    void save() {
        var category = EntityTestUtil.getCategory();
        var author = EntityTestUtil.getAuthor();
        var book = EntityTestUtil.getBook(category, author);
        var user = EntityTestUtil.getUser();
        var order = EntityTestUtil.getOrder(book, user);

        session.save(category);
        session.save(author);
        session.save(book);
        session.save(user);

        var actualOrder = orderRepository.save(order);

        assertThat(actualOrder.getId()).isNotNull();
    }

    @Test
    void delete() {
        var category = EntityTestUtil.getCategory();
        var author = EntityTestUtil.getAuthor();
        var book = EntityTestUtil.getBook(category, author);
        var user = EntityTestUtil.getUser();
        var order = EntityTestUtil.getOrder(book, user);

        session.save(category);
        session.save(author);
        session.save(book);
        session.save(user);

        orderRepository.save(order);
        orderRepository.delete(order);
        session.clear();

        var deletedOrder = orderRepository.findById(order.getId());

        assertThat(deletedOrder).isEmpty();
    }

    @Test
    void update() {
        var expectedOrder = orderRepository.findById(1L).get();
        expectedOrder.setType(OrderType.READING_ROOM);
        orderRepository.update(expectedOrder);
        session.clear();

        var actualOrder = orderRepository.findById(1L);

        assertThat(actualOrder).isPresent();
        assertThat(actualOrder.get().getType()).isEqualTo(OrderType.READING_ROOM);
    }

    @Test
    void findByFilterQueryDslWithAllParameters() {
        var filter = OrderFilter.builder()
                .type(OrderType.READING_ROOM)
                .status(OrderStatus.ORDERED)
                .user("eva@gmail.com")
                .book("The Premature Burial")
                .orderedDate(LocalDateTime.of(2018, 4, 22, 5, 24))
                .build();

        var orders = orderRepository.findByFilterQueryDsl(filter);

        assertNotNull(orders);
        assertThat(orders).hasSize(1);
        assertThat(orders.get(0).getId()).isEqualTo(4);
    }

    @Test
    void findByFilterQueryDslWithTwoParameters() {
        var filter = OrderFilter.builder()
                .type(OrderType.READING_ROOM)
                .status(OrderStatus.ORDERED)
                .build();

        var orders = orderRepository.findByFilterQueryDsl(filter);

        assertNotNull(orders);
        assertThat(orders).hasSize(2);
        assertThat(orders.get(0).getId()).isEqualTo(2);
        assertThat(orders.get(1).getId()).isEqualTo(4);
    }

    @Test
    void findByFilterQueryDslWithoutParameters() {
        var filter = OrderFilter.builder()
                .build();

        var orders = orderRepository.findByFilterQueryDsl(filter);

        assertThat(orders).hasSize(orderRepository.findAll().size());
    }
}
