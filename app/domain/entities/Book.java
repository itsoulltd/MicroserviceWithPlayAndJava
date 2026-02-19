package domain.entities;

import com.infoworks.entity.Column;
import com.infoworks.entity.PrimaryKey;
import com.infoworks.entity.TableName;
import domain.models.PublicationStatus;

import java.util.Objects;

@TableName(value = "books", acceptAll = false)
public class Book extends Auditable {

    @PrimaryKey(name = "id", auto = true)
    private long id;
    @Column(name = "isbn")
    private String isbn;
    @Column(name = "title")
    private String title;
    @Column(name = "subtitle")
    private String subtitle;
    @Column(name = "copyright_year")
    private String copyrightYear;
    @Column(name = "status")
    private String status = PublicationStatus.None.value();

    public Book() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id &&
                isbn.equals(book.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isbn);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getCopyrightYear() {
        return copyrightYear;
    }

    public void setCopyrightYear(String copyrightYear) {
        this.copyrightYear = copyrightYear;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
