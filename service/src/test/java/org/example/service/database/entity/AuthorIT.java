package org.example.service.database.entity;

import org.example.service.integration.IntegrationTestBase;
import org.example.service.util.EntityTestUtil;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AuthorIT extends IntegrationTestBase {

    @Test
    void saveAuthor() {
        var author = EntityTestUtil.getAuthor();
        session.save(author);

        assertThat(author.getId()).isNotNull();
    }

    @Test
    void getAuthorById() {
        var expectedAuthor = EntityTestUtil.getAuthor();
        session.save(expectedAuthor);

        session.clear();

        var actualAuthor = session.get(Author.class, expectedAuthor.getId());

        assertThat(expectedAuthor).isEqualTo(actualAuthor);
    }

    @Test
    void updateAuthor() {
        var expectedAuthor = EntityTestUtil.getAuthor();
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
        var author = EntityTestUtil.getAuthor();
        session.save(author);

        session.delete(author);
        session.flush();
        session.clear();

        var deletedAuthor = session.get(Author.class, author.getId());

        assertThat(deletedAuthor).isNull();
    }
}
