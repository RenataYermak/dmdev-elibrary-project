package org.example.service.dao;

import com.querydsl.jpa.impl.JPAQuery;
import org.example.service.database.entity.Order;
import org.example.service.dto.OrderFilter;

import javax.persistence.EntityManager;
import java.util.List;

import static org.example.service.database.entity.QBook.book;
import static org.example.service.database.entity.QOrder.order;
import static org.example.service.database.entity.QUser.user;

public class OrderRepository extends BaseRepository<Long, Order> {

    public OrderRepository(Class<Order> clazz, EntityManager entityManager) {
        super(clazz, entityManager);
    }

    public List<Order> findByFilterQueryDsl(OrderFilter filter) {
        var predicate = QPredicate.builder()
                .add(filter.getType(), order.type::eq)
                .add(filter.getStatus(), order.status::eq)
                .add(filter.getUser(), order.user.email::eq)
                .add(filter.getBook(), order.book.title::eq)
                .add(filter.getOrderedDate(), order.orderedDate::eq)
                .buildAnd();

        return new JPAQuery<Order>(entityManager)
                .select(order)
                .from(order)
                .join(order.user, user)
                .join(order.book, book)
                .where(predicate)
                .fetch();
    }
}
