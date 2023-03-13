package org.example.service.database.entity;

import org.example.service.integration.IntegrationTestBase;
import org.example.service.util.EntityTestUtil;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CategoryIT extends IntegrationTestBase {

    @Test
    void saveCategory() {
        var category = EntityTestUtil.getCategory();
        session.save(category);

        assertThat(category.getId()).isNotNull();
    }

    @Test
    void getCategoryById() {
        var expectedCategory = EntityTestUtil.getCategory();
        session.save(expectedCategory);

        session.clear();

        var actualCategory = session.get(Category.class, expectedCategory.getId());

        assertThat(expectedCategory).isEqualTo(actualCategory);
    }

    @Test
    void updateCategory() {
        var expectedCategory = EntityTestUtil.getCategory();
        session.save(expectedCategory);

        expectedCategory.setName("Fantasy");
        session.update(expectedCategory);
        session.flush();
        session.clear();

        var actualCategory = session.get(Category.class, expectedCategory.getId());

        assertThat(expectedCategory).isEqualTo(actualCategory);
    }

    @Test
    void deleteCategory() {
        var category = EntityTestUtil.getCategory();
        session.save(category);

        session.delete(category);
        session.flush();
        session.clear();

        var deletedCategory = session.get(Category.class, category.getId());

        assertThat(deletedCategory).isNull();
    }
}
