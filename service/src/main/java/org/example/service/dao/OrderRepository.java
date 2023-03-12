package org.example.service.dao;

import org.example.service.database.entity.Order;

import javax.persistence.EntityManager;

public class OrderRepository extends BaseRepository<Long, Order> {

    public OrderRepository(EntityManager entityManager) {
        super(Order.class, entityManager);
    }
}
