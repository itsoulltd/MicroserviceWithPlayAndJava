package domain.repositories;

import domain.entities.Book;
import domain.repositories.impl.JDBCRepository;

public class BookRepository extends JDBCRepository<Long, Book> {
}
