package org.example.service.database.entity;

import org.example.service.util.ConfigurationTestUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public abstract class EntityTestBase {

	private static SessionFactory sessionFactory;
	protected Session session;

	@BeforeAll
	static void init() {
		sessionFactory = ConfigurationTestUtil.buildSessionFactory();
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
