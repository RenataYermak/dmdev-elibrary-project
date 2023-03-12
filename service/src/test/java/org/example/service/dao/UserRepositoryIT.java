package org.example.service.dao;

import org.example.service.database.entity.Role;
import org.example.service.dto.UserFilter;
import org.example.service.integration.IntegrationTestBase;
import org.example.service.util.EntityTestUtil;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserRepositoryIT extends IntegrationTestBase {

    @Test
    void findById() {
        var userRepository = new UserRepository(session);

        var actualUser = userRepository.findById(1L);

        assertThat(actualUser).isPresent();
        assertThat(actualUser.get().getEmail()).isEqualTo("renatayermak@gmail.com");
    }

    @Test
    void findAll() {
        var userRepository = new UserRepository(session);

        var users = userRepository.findAll();

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
    void deleteExistingUser() {
        var userRepository = new UserRepository(session);

        userRepository.delete(1L);

        assertThat(userRepository.findById(1L)).isEmpty();
    }

    @Test
    void deleteNotExistingUser() {
        var userRepository = new UserRepository(session);

        assertThrows(IllegalArgumentException.class, () -> userRepository.delete(100500900L));
    }

    @Test
    void update() {
        var userRepository = new UserRepository(session);

        var expectedUser = userRepository.findById(1L).get();
        expectedUser.setEmail("newemail@gmail.com");
        userRepository.update(expectedUser);

        var actualUser = userRepository.findById(1L);

        assertThat(actualUser).isPresent();
        assertThat(actualUser.get().getEmail()).isEqualTo("newemail@gmail.com");
    }

    @Test
    void findExistUserByEmailAndPassword() {
        var userRepository = new UserRepository(session);

        var email = "renatayermak@gmail.com";
        var password = "1212";

        var users = userRepository.findAllByEmailAndPassword(email, password);

        assertNotNull(users);
        assertThat(users).hasSize(1);
        assertThat(users.get(0).getId()).isEqualTo(1);
    }

    @Test
    void findNotExistUserByEmailAndPassword() {
        var userRepository = new UserRepository(session);

        var email = "yermakrenata@gmail.com";
        var password = "1313";
        var users = userRepository.findAllByEmailAndPassword(email, password);

        assertThat(users).hasSize(0);
    }

    @Test
    void findUsersByQueryWithAllParameters() {
        var userRepository = new UserRepository(session);

        var filter = UserFilter.builder()
                .email("alex@gmail.com")
                .firstname("Alex")
                .lastname("Yermak")
                .role(Role.USER)
                .build();

        var users = userRepository.findUsersByQuery(filter);

        assertNotNull(users);
        assertThat(users).hasSize(1);
        assertThat(users.get(0).getId()).isEqualTo(2);
    }

    @Test
    void findUsersByQueryWithOneParameters() {
        var userRepository = new UserRepository(session);

        var filter = UserFilter.builder()
                .role(Role.USER)
                .build();

        var users = userRepository.findUsersByQuery(filter);

        assertNotNull(users);
        assertThat(users).hasSize(3);
        assertThat(users.get(0).getId()).isEqualTo(2);
        assertThat(users.get(1).getId()).isEqualTo(3);
        assertThat(users.get(2).getId()).isEqualTo(4);
    }

    @Test
    void findUsersByQueryWithoutParameters() {
        var userRepository = new UserRepository(session);

        var filter = UserFilter.builder()
                .build();

        var users = userRepository.findUsersByQuery(filter);

        assertNotNull(users);
        assertThat(users).hasSize(userRepository.findAll().size());
    }
}
