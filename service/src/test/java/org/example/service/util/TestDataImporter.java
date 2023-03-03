package org.example.service.util;

import lombok.experimental.UtilityClass;
import lombok.Cleanup;
import org.example.service.database.entity.Author;
import org.example.service.database.entity.Book;
import org.example.service.database.entity.Category;
import org.example.service.database.entity.Order;
import org.example.service.database.entity.OrderStatus;
import org.example.service.database.entity.OrderType;
import org.example.service.database.entity.Role;
import org.example.service.database.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.time.LocalDateTime;

@UtilityClass
public class TestDataImporter {

    public void importData(SessionFactory sessionFactory) {
        @Cleanup Session session = sessionFactory.openSession();

        Category drama = saveCategory(session, "Drama");
        Category horror = saveCategory(session, "Horror");
        Category detective = saveCategory(session, "Detective");

        Author stephanKing = saveAuthor(session, "Stephan King");
        Author conanDoyle = saveAuthor(session, "Conan Doyle");
        Author edgarAllanPoe = saveAuthor(session, "Edgar Allan Poe");
        Author agathaChristie = saveAuthor(session, "Agatha Christie");

        Book deathOnTheNile = saveBook(session, "Death on the Nile", 1937,
                "description", 5, detective, agathaChristie);
        Book thePrematureBurial = saveBook(session, "The Premature Burial", 1962,
                "description", 2, drama, edgarAllanPoe);
        Book theMemoirsOfSherlockHolmes = saveBook(session, "The Memoirs of Sherlock Holmes", 1893,
                "description", 7, detective, conanDoyle);
        Book theShining = saveBook(session, "The Shining", 1977,
                "description", 8, horror, stephanKing);

        User renata = saveUser(session, "Renata", "Yermak",
                "renata@gmail.com", "1212", Role.ADMIN);
        User alex = saveUser(session, "Alex", "Yermak",
                "alex@gmail.com", "3333", Role.USER);
        User nikita = saveUser(session, "Nikita", "Shturo",
                "nikita@gmail.com", "2222", Role.USER);
        User eva = saveUser(session, "Eva", "Shturo",
                "eva@gmail.com", "1111", Role.USER);

        Order one = saveOrder(session, deathOnTheNile, renata, OrderStatus.ORDERED,
                OrderType.READING_ROOM, LocalDateTime.now());
        Order two = saveOrder(session, theShining, renata, OrderStatus.ORDERED,
                OrderType.READING_ROOM, LocalDateTime.now());
        Order three = saveOrder(session, deathOnTheNile, alex, OrderStatus.ORDERED,
                OrderType.READING_ROOM, LocalDateTime.now());
        Order four = saveOrder(session, thePrematureBurial, eva, OrderStatus.ORDERED,
                OrderType.READING_ROOM, LocalDateTime.now());

    }

    private Category saveCategory(Session session, String name) {
        Category category = Category.builder()
                .name(name)
                .build();
        session.save(category);

        return category;
    }

    private Author saveAuthor(Session session, String name) {
        Author author = Author.builder()
                .name(name)
                .build();
        session.save(author);

        return author;
    }

    private Book saveBook(Session session,
                          String title,
                          Integer publishYear,
                          String description,
                          Integer number,
                          Category category,
                          Author author) {
        Book book = Book.builder()
                .title(title)
                .author(author)
                .publishYear(publishYear)
                .category(category)
                .description(description)
                .number(number)
                .build();
        session.save(book);

        return book;
    }

    private User saveUser(Session session,
                          String firstname,
                          String lastname,
                          String email,
                          String password,
                          Role role) {
        User user = User.builder()
                .firstname(firstname)
                .lastname(lastname)
                .email(email)
                .password(password)
                .role(role)
                .build();
        session.save(user);

        return user;
    }

    private Order saveOrder(Session session,
                            Book book,
                            User user,
                            OrderStatus status,
                            OrderType type,
                            LocalDateTime orderedDate) {
        Order order = Order.builder()
                .book(book)
                .user(user)
                .status(status)
                .type(type)
                .orderedDate(orderedDate)
                .build();
        session.save(order);

        return order;
    }
}
