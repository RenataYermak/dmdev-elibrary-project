package org.example.service.integration;

import org.example.service.util.ConfigurationTestUtil;
import org.example.service.util.TestDataImporter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public abstract class IntegrationTestBase {

	private static SessionFactory sessionFactory;
	protected Session session;

	@BeforeAll
	static void init() {
		sessionFactory = ConfigurationTestUtil.buildSessionFactory();
        TestDataImporter.importData(sessionFactory);
	}

	@AfterAll
	static void close() {
		sessionFactory.close();
	}

	@BeforeEach
	void sessionInit() {
		session = sessionFactory.openSession();
		session.beginTransaction();
	}

	@AfterEach
	void setSessionDestroy() {
		session.getTransaction().rollback();
		session.close();
	}
}
