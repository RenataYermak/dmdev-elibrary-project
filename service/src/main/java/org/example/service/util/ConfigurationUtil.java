package org.example.service.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.cfg.Configuration;

public class ConfigurationUtil {
	public static SessionFactory buildSessionFactory() {
		Configuration configuration = new Configuration();
		configuration.setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy());
		configuration.configure();
		return configuration.buildSessionFactory();
	}
}
