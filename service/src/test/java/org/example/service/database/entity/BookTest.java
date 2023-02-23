package org.example.service.database.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.service.util.EntityTestUtil.getAuthor;
import static org.example.service.util.EntityTestUtil.getBook;
import static org.example.service.util.EntityTestUtil.getCategory;

public class BookTest extends EntityTestBase {

	@Test
	void saveAndGetBook() {
		var category = getCategory();
		var author = getAuthor();
		var expectedBook = getBook(category, author);
		session.save(category);
		session.save(author);
		session.save(expectedBook);

		var actualBook = session.get(Book.class, expectedBook.getId());

		assertThat(actualBook).isEqualTo(expectedBook);
	}

	@Test
	void updateBook() {
		var category = getCategory();
		var author = getAuthor();
		var expectedBook = getBook(category, author);
		session.save(category);
		session.save(author);
		session.save(expectedBook);

		var actualBook = session.get(Book.class, expectedBook.getId());
		actualBook.setTitle("New Title");
		session.update(actualBook);

		assertThat(actualBook).isEqualTo(expectedBook);
	}

	@Test
	void deleteBook() {
		var category = getCategory();
		var author = getAuthor();
		var book = getBook(category, author);
		session.save(category);
		session.save(author);
		session.save(book);
		session.delete(book);

		var deletedBook = session.get(Book.class, book.getId());

		assertThat(deletedBook).isNull();
	}
}
