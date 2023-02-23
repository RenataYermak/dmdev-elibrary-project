package org.example.service.database.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.service.util.EntityTestUtil.getCategory;

public class CategoryTest extends EntityTestBase {

	@Test
	void saveAndGetCategory() {
		var expectedCategory = getCategory();
		session.save(expectedCategory);

		var actualCategory = session.get(Category.class, expectedCategory.getId());

		assertThat(actualCategory).isEqualTo(expectedCategory);
	}

	@Test
	void updateCategory() {
		var expectedCategory = getCategory();
		session.save(expectedCategory);

		var actualCategory = session.get(Category.class, expectedCategory.getId());
		actualCategory.setName("Horror");
		session.update(actualCategory);

		assertThat(actualCategory).isEqualTo(expectedCategory);
	}

	@Test
	void deleteCategory() {
		var category = getCategory();
		session.save(category);
		session.delete(category);

		var deletedCategory = session.get(Category.class, category.getId());

		assertThat(deletedCategory).isNull();
	}
}
