package org.example.service.dao;

import org.example.service.database.entity.Author;
import org.example.service.integration.IntegrationTestBase;
import org.example.service.util.EntityTestUtil;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AuthorRepositoryIT extends IntegrationTestBase {

    AuthorRepository authorRepository = new AuthorRepository(Author.class, session);

    @Test
    void findById() {
        var actualAuthor = authorRepository.findById(1L);
        session.clear();

        assertThat(actualAuthor).isPresent();
        assertThat(actualAuthor.get().getName()).isEqualTo("Stephan King");
    }

    @Test
    void findAll() {
        var authors = authorRepository.findAll();
        session.clear();

        assertNotNull(authors);
        assertThat(authors).hasSize(4);
    }

    @Test
    void save() {
        var author = EntityTestUtil.getAuthor();

        var actualAuthor = authorRepository.save(author);

        assertThat(actualAuthor.getId()).isNotNull();
    }

    @Test
    void delete() {
        var author = EntityTestUtil.getAuthor();
        authorRepository.save(author);
        authorRepository.delete(author);
        session.clear();

        var deletedAuthor = authorRepository.findById(author.getId());

        assertThat(deletedAuthor).isEmpty();
    }

    @Test
    void update() {
        var expectedAuthor = authorRepository.findById(1L).get();
        expectedAuthor.setName("Ernest Hemingway");
        authorRepository.update(expectedAuthor);
        session.clear();

        var actualAuthor = authorRepository.findById(1L);

        assertThat(actualAuthor).isPresent();
        assertThat(actualAuthor.get().getName()).isEqualTo("Ernest Hemingway");
    }
}
