package domain.repositories;

import domain.entities.Book;
import domain.repositories.impl.JDBCRepository;
import play.db.Database;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class BookRepository extends JDBCRepository<Long, Book> {

    private Database db;

    @Inject
    public BookRepository(Database db) {
        this.db = db;
    }

    @Override
    protected Database getDb() {
        return this.db;
    }

    @Override
    public Class<Book> getEntityType() {
        return Book.class;
    }

    @Override
    public String getPrimaryKeyName() {
        return "id";
    }
}
