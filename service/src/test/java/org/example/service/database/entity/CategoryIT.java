package org.example.service.database.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.service.util.EntityTestUtil.getCategory;

public class CategoryIT extends EntityTestBase {

	@Test
	void saveAndGetCategory() {
		var expectedCategory = getCategory();
		session.save(expectedCategory);

		session.clear();

		var actualCategory = session.get(Category.class, expectedCategory.getId());

		assertThat(expectedCategory).isEqualTo(actualCategory);
	}

	@Test
	void updateCategory() {
		var expectedCategory = getCategory();
		session.save(expectedCategory);

		expectedCategory.setName("Horror");
		session.flush();
		session.clear();

		var actualCategory = session.get(Category.class, expectedCategory.getId());

		assertThat(expectedCategory).isEqualTo(actualCategory);
	}

	@Test
	void deleteCategory() {
		var category = getCategory();
		session.save(category);

		session.delete(category);
		session.flush();
		session.clear();

		var deletedCategory = session.get(Category.class, category.getId());

		assertThat(deletedCategory).isNull();
	}
}
