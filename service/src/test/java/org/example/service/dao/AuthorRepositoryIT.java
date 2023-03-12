package org.example.service.dao;

import org.example.service.database.entity.Author;
import org.example.service.integration.IntegrationTestBase;
import org.example.service.util.EntityTestUtil;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AuthorRepositoryIT extends IntegrationTestBase {

    @Test
    void findById() {
        var authorRepository = new AuthorRepository(session);

        Optional<Author> actualAuthor = authorRepository.findById(1L);

        assertThat(actualAuthor).isPresent();
        assertThat(actualAuthor.get().getName()).isEqualTo("Stephan King");
    }

    @Test
    void findAll() {
        var authorRepository = new AuthorRepository(session);

        List<Author> authors = authorRepository.findAll();

        assertNotNull(authors);
        assertThat(authors).hasSize(4);
    }

    @Test
    void save() {
        var author = EntityTestUtil.getAuthor();
        var authorRepository = new AuthorRepository(session);

        var actualAuthor = authorRepository.save(author);

        assertThat(actualAuthor).isNotNull();
    }

    @Test
    void delete() {
        var authorRepository = new AuthorRepository(session);

        authorRepository.delete(1L);

        assertThat(authorRepository.findById(1L)).isEmpty();
    }

    @Test
    void update() {
        var authorRepository = new AuthorRepository(session);

        var expectedAuthor = authorRepository.findById(1L).get();
        expectedAuthor.setName("Ernest Hemingway");
        authorRepository.update(expectedAuthor);

        Optional<Author> actualAuthor = authorRepository.findById(1L);

        assertThat(actualAuthor).isPresent();
        assertThat(actualAuthor.get().getName()).isEqualTo("Ernest Hemingway");
    }
}
