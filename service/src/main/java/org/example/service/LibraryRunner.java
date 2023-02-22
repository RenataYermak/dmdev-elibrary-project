package org.example.service;

import org.example.service.database.entity.Author;
import org.example.service.database.entity.Book;
import org.example.service.database.entity.Category;
import org.example.service.database.entity.Order;
import org.example.service.database.entity.OrderStatus;
import org.example.service.database.entity.OrderType;
import org.example.service.database.entity.Role;
import org.example.service.database.entity.User;
import org.example.service.util.ConfigurationUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.time.LocalDateTime;
import java.util.*;

public class LibraryRunner {

	public static void main(String[] args) {

		User user = User.builder()
				.login("Renata1w99w7112")
				.firstname("Renatwaw12")
				.lastname("Yermak")
				.email("renata1w121w@gmail.com")
				.password("12121997")
				.role(Role.USER)
				.build();
		Author author = Author.builder()
				.name("Ernest Hemingwwayw2")
				.build();

		Category category = Category.builder()
				.name("Comedwyw2")
				.build();

		Book book = Book.builder()
				.title("title2")
				.author(author)
				.publishYear(1111)
				.category(category)
				.description("text1")
				.number(12)
				.picture("picture1")
				.build();


		Order order = Order.builder()
				.book(book)
				.user(user)
				.status(OrderStatus.ORDERED)
				.type(OrderType.READING_ROOM)
				.orderedDate(LocalDateTime.now())
				.build();

		try (SessionFactory sessionFactory = ConfigurationUtil.buildSessionFactory();
		     Session session = sessionFactory.openSession()) {
			session.beginTransaction();

			session.saveOrUpdate(author);
			session.saveOrUpdate(category);
			session.saveOrUpdate(book);
			session.saveOrUpdate(user);
			session.saveOrUpdate(order);

			session.getTransaction().commit();
		}
	}
}
