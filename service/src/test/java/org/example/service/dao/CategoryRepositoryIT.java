package org.example.service.dao;

import org.example.service.database.entity.Category;
import org.example.service.integration.IntegrationTestBase;
import org.example.service.util.EntityTestUtil;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CategoryRepositoryIT extends IntegrationTestBase {

    @Test
    void save() {
        Category category = EntityTestUtil.getCategory();
        CategoryRepository categoryRepository = new CategoryRepository(session);

        Category actualCategory = categoryRepository.save(category);

        assertThat(actualCategory).isNotNull();
    }

    @Test
    void delete() {
        CategoryRepository categoryRepository = new CategoryRepository(session);

        categoryRepository.delete(1);

        assertThat(categoryRepository.findById(1)).isEmpty();
    }

    @Test
    void update() {
        CategoryRepository categoryRepository = new CategoryRepository(session);

        Category expectedCategory = categoryRepository.findById(1).get();
        expectedCategory.setName("Science");
        categoryRepository.update(expectedCategory);

        Optional<Category> actualCategory = categoryRepository.findById(1);

        assertThat(actualCategory).isPresent();
        assertThat(actualCategory.get().getName()).isEqualTo("Science");
    }

    @Test
    void findById() {
        CategoryRepository categoryRepository = new CategoryRepository(session);

        Optional<Category> actualCategory = categoryRepository.findById(1);

        assertThat(actualCategory).isPresent();
        assertThat(actualCategory.get().getName()).isEqualTo("Drama");
    }

    @Test
    void findAll() {
        CategoryRepository categoryRepository = new CategoryRepository(session);

        List<Category> categories = categoryRepository.findAll();

        assertNotNull(categories);
        assertThat(categories).hasSize(3);
    }
}
