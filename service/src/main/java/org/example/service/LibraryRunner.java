package org.example.service;

import org.example.service.database.entity.book.Author;
import org.example.service.database.entity.book.Book;
import org.example.service.database.entity.book.Category;
import org.example.service.database.entity.order.Order;
import org.example.service.database.entity.order.OrderStatus;
import org.example.service.database.entity.order.OrderType;
import org.example.service.database.entity.user.Role;
import org.example.service.database.entity.user.User;
import org.example.service.util.ConfigurationUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.time.LocalDateTime;

public class LibraryRunner {

	public static void main(String[] args) {

		User user = User.builder()
				.login("Renata1997")
				.firstname("Renata")
				.lastname("Yermak")
				.email("renata1@gmail.com")
				.password("12121997")
				.role(Role.USER)
				.build();

		Book book = Book.builder()
				.title("title2")
				.author(3)
				.publishYear(1955)
				.category(1)
				.description("text1")
				.number(12)
				.picture("picture1")
				.build();

		Author author = Author.builder()
				.name("Ernest Hemingway")
				.build();

		Category category = Category.builder()
				.name("Drama")
				.build();

		Order order = Order.builder()
				.book(2)
				.user(2)
				.status(OrderStatus.ORDERED)
				.type(OrderType.SEASON_TICKET)
				.orderedDate(LocalDateTime.now())
				.build();

		try (SessionFactory sessionFactory = ConfigurationUtil.buildSessionFactory();
		     Session session = sessionFactory.openSession()) {
			session.beginTransaction();

			session.saveOrUpdate(order);

			session.getTransaction().commit();
		}
	}
}
