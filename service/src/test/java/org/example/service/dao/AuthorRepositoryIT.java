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
    void save() {
        Author author = EntityTestUtil.getAuthor();
        AuthorRepository authorRepository = new AuthorRepository(session);

        Author actualAuthor = authorRepository.save(author);

        assertThat(actualAuthor).isNotNull();
    }

    @Test
    void delete() {
        AuthorRepository authorRepository = new AuthorRepository(session);

        authorRepository.delete(1L);

        assertThat(authorRepository.findById(1L)).isEmpty();
    }

    @Test
    void update() {
        AuthorRepository authorRepository = new AuthorRepository(session);

        Author expectedAuthor = authorRepository.findById(1L).get();
        expectedAuthor.setName("Ernest Hemingway");
        authorRepository.update(expectedAuthor);

        Optional<Author> actualAuthor = authorRepository.findById(1L);

        assertThat(actualAuthor).isPresent();
        assertThat(actualAuthor.get().getName()).isEqualTo("Ernest Hemingway");
    }

    @Test
    void findById() {
        AuthorRepository authorRepository = new AuthorRepository(session);

        Optional<Author> actualAuthor = authorRepository.findById(1L);

        assertThat(actualAuthor).isPresent();
        assertThat(actualAuthor.get().getName()).isEqualTo("Stephan King");
    }

    @Test
    void findAll() {
        AuthorRepository authorRepository = new AuthorRepository(session);

        List<Author> authors = authorRepository.findAll();

        assertNotNull(authors);
        assertThat(authors).hasSize(4);
    }
}
