package org.example.service.util;

import lombok.experimental.UtilityClass;
import org.hibernate.SessionFactory;
import org.testcontainers.containers.PostgreSQLContainer;

@UtilityClass
public class ConfigurationTestUtil {

    private static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15");

    static {
        postgres.start();
    }

    public static SessionFactory buildSessionFactory() {
        var configuration = ConfigurationUtil.buildConfiguration();
        configuration.setProperty("hibernate.connection.url", postgres.getJdbcUrl());
        configuration.setProperty("hibernate.connection.username", postgres.getUsername());
        configuration.setProperty("hibernate.connection.password", postgres.getPassword());
        configuration.configure();

        return configuration.buildSessionFactory();
    }
}
