package org.example.service.util;

import org.example.service.database.entity.*;

import java.time.LocalDateTime;

public class EntityTestUtil {

	public static User getUser() {
		return User.builder()
				.login("Renata1212")
				.firstname("Renata")
				.lastname("Yermak")
				.email("renata@gmail.com")
				.password("12121997")
				.role(Role.USER)
				.build();
	}

	public static Author getAuthor() {
		return Author.builder()
				.name("Ernest Hemingway")
				.build();
	}

	public static Category getCategory() {
		return Category.builder()
				.name("Novel")
				.build();
	}

	public static Book getBook(Category category, Author author) {
		return Book.builder()
				.title("title")
				.author(author)
				.publishYear(2000)
				.category(category)
				.description("text")
				.number(12)
				.picture("picture")
				.build();
	}

	public static Order getOrder(Book book, User user) {
		return Order.builder()
				.book(book)
				.user(user)
				.status(OrderStatus.ORDERED)
				.type(OrderType.READING_ROOM)
				.orderedDate(LocalDateTime.now())
				.build();
	}
}
