package org.example.service.database.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.service.util.EntityTestUtil.getAuthor;

public class AuthorTest extends EntityTestBase {

	@Test
	void saveAndGetAuthor() {
		var expectedAuthor = getAuthor();
		session.save(expectedAuthor);

		var actualAuthor = session.get(Author.class, expectedAuthor.getId());

		assertThat(actualAuthor).isEqualTo(expectedAuthor);
	}

	@Test
	void updateAuthor() {
		var expectedAuthor = getAuthor();
		session.save(expectedAuthor);

		var actualAuthor = session.get(Author.class, expectedAuthor.getId());
		actualAuthor.setName("Stephen King");
		session.update(actualAuthor);

		assertThat(expectedAuthor).isEqualTo(actualAuthor);
	}

	@Test
	void deleteAuthor() {
		var author = getAuthor();
		session.save(author);
		session.delete(author);

		var deletedAuthor = session.get(Author.class, author.getId());

		assertThat(deletedAuthor).isNull();
	}
}
