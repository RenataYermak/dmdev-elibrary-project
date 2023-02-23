package org.example.service.database.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.service.util.EntityTestUtil.getUser;

public class UserTest extends EntityTestBase {

	@Test
	void saveAndGetUser() {
		var expectedUser = getUser();
		session.save(expectedUser);

		var actualUser = session.get(User.class, expectedUser.getId());

		assertThat(actualUser).isEqualTo(expectedUser);
	}

	@Test
	void updateUser() {
		var expectedUser = getUser();
		session.save(expectedUser);

		var actualUser = session.get(User.class, expectedUser.getId());
		actualUser.setFirstname("Alex");
		session.update(actualUser);

		assertThat(actualUser).isEqualTo(expectedUser);
	}

	@Test
	void deleteUser() {
		var user = getUser();
		session.save(user);
		session.delete(user);

		var deletedUser = session.get(User.class, user.getId());

		assertThat(deletedUser).isNull();
	}
}
