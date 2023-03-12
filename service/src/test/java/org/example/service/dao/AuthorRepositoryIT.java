package org.example.service.dao;

import org.example.service.integration.IntegrationTestBase;
import org.example.service.util.EntityTestUtil;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AuthorRepositoryIT extends IntegrationTestBase {

    @Test
    void findById() {
        var authorRepository = new AuthorRepository(session);

        var actualAuthor = authorRepository.findById(1L);

        assertThat(actualAuthor).isPresent();
        assertThat(actualAuthor.get().getName()).isEqualTo("Stephan King");
    }

    @Test
    void findAll() {
        var authorRepository = new AuthorRepository(session);

        var authors = authorRepository.findAll();

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
    void deleteExistingAuthor() {
        var authorRepository = new AuthorRepository(session);

        authorRepository.delete(1L);

        assertThat(authorRepository.findById(1L)).isEmpty();
    }

    @Test
    void deleteNotExistingAuthor() {
        var authorRepository = new AuthorRepository(session);

        assertThrows(IllegalArgumentException.class, () -> authorRepository.delete(100500900L));
    }

    @Test
    void update() {
        var authorRepository = new AuthorRepository(session);

        var expectedAuthor = authorRepository.findById(1L).get();
        expectedAuthor.setName("Ernest Hemingway");
        authorRepository.update(expectedAuthor);

        var actualAuthor = authorRepository.findById(1L);

        assertThat(actualAuthor).isPresent();
        assertThat(actualAuthor.get().getName()).isEqualTo("Ernest Hemingway");
    }
}
