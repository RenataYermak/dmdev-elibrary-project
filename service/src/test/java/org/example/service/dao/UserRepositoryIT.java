package org.example.service.dao;

import org.example.service.database.entity.User;
import org.example.service.integration.IntegrationTestBase;
import org.example.service.util.EntityTestUtil;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserRepositoryIT extends IntegrationTestBase {

    @Test
    void findById() {
        var userRepository = new UserRepository(session);

        Optional<User> actualUser = userRepository.findById(1L);

        assertThat(actualUser).isPresent();
        assertThat(actualUser.get().getEmail()).isEqualTo("renatayermak@gmail.com");
    }

    @Test
    void findAll() {
        var userRepository = new UserRepository(session);

        List<User> users = userRepository.findAll();

        assertNotNull(users);
        assertThat(users).hasSize(4);
    }

    @Test
    void save() {
        var user = EntityTestUtil.getUser();
        var userRepository = new UserRepository(session);

        var actualUser = userRepository.save(user);

        assertThat(actualUser).isNotNull();
    }

    @Test
    void delete() {
        var userRepository = new UserRepository(session);

        userRepository.delete(1L);

        assertThat(userRepository.findById(1L)).isEmpty();
    }

    @Test
    void update() {
        var userRepository = new UserRepository(session);

        var expectedUser = userRepository.findById(1L).get();
        expectedUser.setEmail("newemail@gmail.com");
        userRepository.update(expectedUser);

        Optional<User> actualUser = userRepository.findById(1L);

        assertThat(actualUser).isPresent();
        assertThat(actualUser.get().getEmail()).isEqualTo("newemail@gmail.com");
    }
}
