package domain.repositories;

import com.infoworks.entity.EntityMapper;
import domain.entities.Book;
import domain.repositories.impl.JdbcRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.db.Database;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.sql.ResultSet;
import java.sql.SQLException;

@Singleton
public class BookRepository extends JdbcRepository<Long, Book> implements EntityMapper<Book> {

    private static Logger LOG = LoggerFactory.getLogger(BookRepository.class);
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

    @Override
    protected EntityMapper<Book> getMapper() {
        return this;
    }

    @Override
    public Book entity(ResultSet rs, int columnCount, int rowIdx) throws SQLException {
        Book book = new Book();
        book.setCreatedBy(rs.getString("created_by"));
        book.setCreatedDate(rs.getTimestamp("created_date").toLocalDateTime());
        book.setModifiedBy(rs.getString("modified_by"));
        book.setModifiedDate(rs.getTimestamp("modified_date").toLocalDateTime());
        book.setVersion(rs.getLong("version"));
        book.setId(rs.getLong("id"));
        book.setIsbn(rs.getString("isbn"));
        book.setTitle(rs.getString("title"));
        book.setSubtitle(rs.getString("subtitle"));
        book.setCopyrightYear(rs.getString("copyright_year"));
        book.setStatus(rs.getString("status"));
        return book;
    }
}
