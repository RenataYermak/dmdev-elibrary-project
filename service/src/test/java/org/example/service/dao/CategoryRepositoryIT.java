package org.example.service.dao;

import org.example.service.integration.IntegrationTestBase;
import org.example.service.util.EntityTestUtil;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CategoryRepositoryIT extends IntegrationTestBase {

    @Test
    void findById() {
        var categoryRepository = new CategoryRepository(session);

        var actualCategory = categoryRepository.findById(1);

        assertThat(actualCategory).isPresent();
        assertThat(actualCategory.get().getName()).isEqualTo("Drama");
    }

    @Test
    void findAll() {
        var categoryRepository = new CategoryRepository(session);

        var categories = categoryRepository.findAll();

        assertNotNull(categories);
        assertThat(categories).hasSize(3);
    }

    @Test
    void save() {
        var category = EntityTestUtil.getCategory();
        var categoryRepository = new CategoryRepository(session);

        var actualCategory = categoryRepository.save(category);

        assertThat(actualCategory).isNotNull();
    }

    @Test
    void deleteExistCategory() {
        var categoryRepository = new CategoryRepository(session);

        categoryRepository.delete(1);

        assertThat(categoryRepository.findById(1)).isEmpty();
    }

    @Test
    void deleteNotExistingCategory() {
        var categoryRepository = new CategoryRepository(session);

        assertThrows(IllegalArgumentException.class, () -> categoryRepository.delete(100500900));
    }

    @Test
    void update() {
        var categoryRepository = new CategoryRepository(session);

        var expectedCategory = categoryRepository.findById(1).get();
        expectedCategory.setName("Science");
        categoryRepository.update(expectedCategory);

        var actualCategory = categoryRepository.findById(1);

        assertThat(actualCategory).isPresent();
        assertThat(actualCategory.get().getName()).isEqualTo("Science");
    }
}
