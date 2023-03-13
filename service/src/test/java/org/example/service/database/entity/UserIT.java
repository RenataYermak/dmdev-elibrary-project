package org.example.service.database.entity;

import org.example.service.integration.IntegrationTestBase;
import org.example.service.util.EntityTestUtil;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserIT extends IntegrationTestBase {

    @Test
    void saveUser() {
        var user = EntityTestUtil.getUser();
        session.save(user);

        assertThat(user.getId()).isNotNull();
    }

    @Test
    void getUserById() {
        var expectedUser = EntityTestUtil.getUser();
        session.save(expectedUser);
        session.clear();

        var actualUser = session.get(User.class, expectedUser.getId());

        assertThat(expectedUser).isEqualTo(actualUser);
    }

    @Test
    void updateUser() {
        var expectedUser = EntityTestUtil.getUser();
        session.save(expectedUser);

        expectedUser.setFirstname("Alex");
        session.update(expectedUser);
        session.flush();
        session.clear();

        var actualUser = session.get(User.class, expectedUser.getId());

        assertThat(expectedUser.getFirstname()).isEqualTo(actualUser.getFirstname());
    }

    @Test
    void deleteUser() {
        var user = EntityTestUtil.getUser();
        session.save(user);

        session.delete(user);
        session.flush();
        session.clear();

        var deletedUser = session.get(User.class, user.getId());

        assertThat(deletedUser).isNull();
    }
}
