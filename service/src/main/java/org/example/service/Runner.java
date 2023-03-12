package org.example.service;

import org.example.service.dao.UserRepository;
import org.example.service.util.ConfigurationUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.lang.reflect.Proxy;

public class Runner {
    public static void main(String[] args) {
        try (SessionFactory sessionFactory = ConfigurationUtil.buildSessionFactory()) {
            var session = (Session) Proxy.newProxyInstance(SessionFactory.class.getClassLoader(), new Class[]{Session.class},
                    (proxy, method, args1) -> method.invoke(sessionFactory.getCurrentSession(), args1));
            session.beginTransaction();

            UserRepository userRepository = new UserRepository(session);
            userRepository.findById(5L).ifPresent(System.out::println);


            session.getTransaction().commit();
        }
    }
}
