package org.example.service.integration;

import org.example.service.util.ConfigurationTestUtil;
import org.example.service.util.TestDataImporter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.lang.reflect.Proxy;

public abstract class IntegrationTestBase {

    private static SessionFactory sessionFactory;
    protected static Session session;

    @BeforeAll
    static void init() {
        sessionFactory = ConfigurationTestUtil.buildSessionFactory();
        session = (Session) Proxy.newProxyInstance(SessionFactory.class.getClassLoader(), new Class[]{Session.class},
                (proxy, method, args) -> method.invoke(sessionFactory.getCurrentSession(), args));
        TestDataImporter.importData(sessionFactory);
    }

    @BeforeEach
    void getSession() {
        session.beginTransaction();
    }

    @AfterEach
    void closeSession() {
        session.getTransaction().rollback();
    }

    @AfterAll
    static void close() {
        sessionFactory.close();
    }
}
