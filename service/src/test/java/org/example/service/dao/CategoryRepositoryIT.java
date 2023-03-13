package org.example.service.dao;

import org.example.service.database.entity.Category;
import org.example.service.integration.IntegrationTestBase;
import org.example.service.util.EntityTestUtil;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CategoryRepositoryIT extends IntegrationTestBase {

    CategoryRepository categoryRepository = new CategoryRepository(Category.class, session);

    @Test
    void findById() {
        var actualCategory = categoryRepository.findById(1);
        session.clear();

        assertThat(actualCategory).isPresent();
        assertThat(actualCategory.get().getName()).isEqualTo("Drama");
    }

    @Test
    void findAll() {
        var categories = categoryRepository.findAll();
        session.clear();

        assertNotNull(categories);
        assertThat(categories).hasSize(3);
    }

    @Test
    void save() {
        var category = EntityTestUtil.getCategory();

        var actualCategory = categoryRepository.save(category);

        assertThat(actualCategory.getId()).isNotNull();
    }

    @Test
    void delete() {
        var category = EntityTestUtil.getCategory();
        categoryRepository.save(category);

        categoryRepository.delete(category);
        session.clear();

        var deletedCategory = categoryRepository.findById(category.getId());

        assertThat(deletedCategory).isEmpty();
    }

    @Test
    void update() {
        var expectedCategory = categoryRepository.findById(1).get();
        expectedCategory.setName("Science");
        categoryRepository.update(expectedCategory);
        session.clear();

        var actualCategory = categoryRepository.findById(1);

        assertThat(actualCategory).isPresent();
        assertThat(actualCategory.get().getName()).isEqualTo("Science");
    }
}
