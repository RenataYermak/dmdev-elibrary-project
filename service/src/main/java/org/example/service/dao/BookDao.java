package org.example.service.dao;

import com.querydsl.jpa.impl.JPAQuery;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.example.service.database.entity.Author_;
import org.example.service.database.entity.Book;
import org.example.service.database.entity.Book_;
import org.example.service.database.entity.Category_;
import org.example.service.dto.BookFilter;
import org.hibernate.Session;
import org.hibernate.graph.GraphSemantic;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

import static org.example.service.database.entity.QAuthor.author;
import static org.example.service.database.entity.QBook.book;
import static org.example.service.database.entity.QCategory.category;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BookDao {

    private static final BookDao INSTANCE = new BookDao();
    private static final String GRAPH_NAME = "WithOrders";

    public static BookDao getInstance() {
        return INSTANCE;
    }

    public List<Book> findAllByFilterQueryDsl(Session session, BookFilter filter) {
        var predicate = QPredicate.builder()
                .add(filter.getPublishYear(), book.publishYear::eq)
                .add(filter.getCategory(), book.category.name::eq)
                .add(filter.getAuthor(), book.author.name::eq)
                .buildAnd();

        return new JPAQuery<Book>(session)
                .select(book)
                .from(book)
                .join(book.author, author)
                .join(book.category, category)
                .where(predicate)
                .setHint(GraphSemantic.LOAD.getJpaHintName(), session.getEntityGraph(GRAPH_NAME))
                .fetch();
    }

    public List<Book> findAllByFilterCriteria(Session session, BookFilter filter) {
        var cb = session.getCriteriaBuilder();

        var criteria = cb.createQuery(Book.class);
        var book = criteria.from(Book.class);

        var author = book.join(Book_.author);
        var category = book.join(Book_.category);


        List<Predicate> predicates = new ArrayList<>();
        if (filter.getPublishYear() != null) {
            predicates.add(cb.equal(book.get(Book_.PUBLISH_YEAR), filter.getPublishYear()));
        }
        if (filter.getCategory() != null) {
            predicates.add(cb.equal(book.get(Book_.CATEGORY).get(Category_.NAME), filter.getCategory()));
        }
        if (filter.getAuthor() != null) {
            predicates.add(cb.equal(book.get(Book_.AUTHOR).get(Author_.NAME), filter.getAuthor()));
        }

        criteria.select(book).where(
                predicates.toArray(Predicate[]::new)
        );

        return session.createQuery(criteria)
                .setHint(GraphSemantic.LOAD.getJpaHintName(), session.getEntityGraph(GRAPH_NAME))
                .list();
    }
}
