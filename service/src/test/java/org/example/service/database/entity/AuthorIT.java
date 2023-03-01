package org.example.service.database.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.service.util.EntityTestUtil.getAuthor;

public class AuthorIT extends EntityTestBase {

    @Test
    void saveAuthor() {
        var author = getAuthor();
        session.save(author);

        session.clear();

        assertThat(author).isNotNull();
    }

    @Test
    void getAuthorById() {
        var expectedAuthor = getAuthor();
        session.save(expectedAuthor);

        session.clear();

        var actualAuthor = session.get(Author.class, expectedAuthor.getId());

        assertThat(expectedAuthor).isEqualTo(actualAuthor);
    }

    @Test
    void updateAuthor() {
        var expectedAuthor = getAuthor();
        session.save(expectedAuthor);

        expectedAuthor.setName("Stephen King");
        session.update(expectedAuthor);
        session.flush();
        session.clear();

        var actualAuthor = session.get(Author.class, expectedAuthor.getId());

        assertThat(expectedAuthor.getName()).isEqualTo(actualAuthor.getName());
    }

    @Test
    void deleteAuthor() {
        var author = getAuthor();
        session.save(author);

        session.delete(author);
        session.flush();
        session.clear();

        var deletedAuthor = session.get(Author.class, author.getId());

        assertThat(deletedAuthor).isNull();
    }
}
