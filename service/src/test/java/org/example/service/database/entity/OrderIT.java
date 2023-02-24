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
	void saveAndGetBook() {
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

		var actualOrder = session.get(Order.class, expectedOrder.getId());

		assertThat(actualOrder).isEqualTo(expectedOrder);
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

		var actualOrder = session.get(Order.class, expectedOrder.getId());
		actualOrder.setType(OrderType.SEASON_TICKET);
		session.update(actualOrder);

		assertThat(actualOrder).isEqualTo(expectedOrder);
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

		var deletedOrder = session.get(Order.class, order.getId());

		assertThat(deletedOrder).isNull();
	}
}
