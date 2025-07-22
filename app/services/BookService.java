package services;

import domain.entities.Book;
import domain.models.BookDTO;
import domain.models.PublicationStatus;
import domain.repositories.BookRepository;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Singleton
public class BookService {

    private BookRepository repository;

    @Inject
    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public long count() {
        long count = repository.count();
        return count;
    }

    public Optional<BookDTO> findById(Long id) {
        final Optional<Book> optionalBook = repository.findById(id);
        return Optional.of(BookDTO.convert(optionalBook.orElse(new Book())));
    }

    public List<BookDTO> findAll(int page, int limit) {
        List<Book> found = repository.findAll(page, limit);
        List<BookDTO> result = found.stream()
                .map(BookDTO::convert)
                .collect(Collectors.toList());
        return result;
    }

    public Optional<BookDTO> save(BookDTO entity) {
        Book book = BookDTO.revert(entity);
        book.setCreatedBy(""); //TODO: getAuthPrincipal
        book.setCreatedDate(LocalDateTime.now());
        book.setModifiedBy(""); //TODO: getAuthPrincipal
        book.setModifiedDate(LocalDateTime.now());
        book.setVersion(0l);
        Optional<Book> saved = repository.save(book);
        return Optional.of(BookDTO.convert(saved.orElse(new Book())));
    }

    public Optional<BookDTO> update(BookDTO entity) {
        Book book = BookDTO.revert(entity);
        book.setModifiedBy(""); //TODO: getAuthPrincipal
        book.setModifiedDate(LocalDateTime.now());
        book.setVersion(1l);    // JPA-Take care!
        Optional<Book> update = repository.update(book);
        return Optional.of(BookDTO.convert(update.orElse(new Book())));
    }

    public boolean delete(Long id) {
        boolean deleted = repository.delete(id);
        return deleted;
    }
}
