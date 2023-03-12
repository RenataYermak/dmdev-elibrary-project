package org.example.service.dao;

import org.example.service.database.entity.Order;
import org.example.service.database.entity.OrderStatus;
import org.example.service.database.entity.OrderType;
import org.example.service.dto.OrderFilter;
import org.example.service.integration.IntegrationTestBase;
import org.example.service.util.EntityTestUtil;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OrderRepositoryIT extends IntegrationTestBase {

    @Test
    void findById() {
        var orderRepository = new OrderRepository(session);

        Optional<Order> actualOrder = orderRepository.findById(1L);

        assertThat(actualOrder).isPresent();
        assertThat(actualOrder.get().getType()).isEqualTo(OrderType.SEASON_TICKET);
    }

    @Test
    void findAll() {
        var orderRepository = new OrderRepository(session);

        List<Order> orders = orderRepository.findAll();

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

        var categoryRepository = new CategoryRepository(session);
        var authorRepository = new AuthorRepository(session);
        var bookRepository = new BookRepository(session);
        var userRepository = new UserRepository(session);
        var orderRepository = new OrderRepository(session);

        categoryRepository.save(category);
        authorRepository.save(author);
        bookRepository.save(book);
        userRepository.save(user);

        var actualOrder = orderRepository.save(order);

        assertThat(actualOrder).isNotNull();
    }

    @Test
    void deleteExistingOrder() {
        var orderRepository = new OrderRepository(session);

        orderRepository.delete(1L);

        assertThat(orderRepository.findById(1L)).isEmpty();
    }

    @Test
    void deleteNotExistingOrder() {
        var orderRepository = new OrderRepository(session);

        assertThrows(IllegalArgumentException.class, () -> orderRepository.delete(100500900L));
    }

    @Test
    void update() {
        var orderRepository = new OrderRepository(session);

        var expectedOrder = orderRepository.findById(1L).get();
        expectedOrder.setType(OrderType.READING_ROOM);
        orderRepository.update(expectedOrder);

        Optional<Order> actualOrder = orderRepository.findById(1L);

        assertThat(actualOrder).isPresent();
        assertThat(actualOrder.get().getType()).isEqualTo(OrderType.READING_ROOM);
    }

    @Test
    void findByFilterQueryDslWithAllParameters() {
        var orderRepository = new OrderRepository(session);

        OrderFilter filter = OrderFilter.builder()
                .type(OrderType.READING_ROOM)
                .status(OrderStatus.ORDERED)
                .user("eva@gmail.com")
                .book("The Premature Burial")
                .orderedDate(LocalDateTime.of(2018,4,22,5,24))
                .build();

        List<Order> orders = orderRepository.findByFilterQueryDsl(filter);

        assertNotNull(orders);
        assertThat(orders).hasSize(1);
        assertThat(orders.get(0).getId()).isEqualTo(4);
    }

    @Test
    void findByFilterQueryDslWithTwoParameters() {
        var orderRepository = new OrderRepository(session);

        OrderFilter filter = OrderFilter.builder()
                .type(OrderType.READING_ROOM)
                .status(OrderStatus.ORDERED)
                .build();

        List<Order> orders = orderRepository.findByFilterQueryDsl(filter);

        assertNotNull(orders);
        assertThat(orders).hasSize(2);
        assertThat(orders.get(0).getId()).isEqualTo(2);
        assertThat(orders.get(1).getId()).isEqualTo(4);
    }

    @Test
    void findByFilterQueryDslWithoutParameters() {
        var orderRepository = new OrderRepository(session);

        OrderFilter filter = OrderFilter.builder()
                .build();

        List<Order> orders = orderRepository.findByFilterQueryDsl(filter);

        assertThat(orders).hasSize(orderRepository.findAll().size());
    }
}
