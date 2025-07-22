package domain.models;

import com.it.soul.lab.sql.entity.Entity;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

public class BookDTO extends Entity {

    private long id;
    @NotEmpty(message = "Isbn must not be null or empty!")
    @Length(max = 40, min = 8, message = "Isbn has to be 8 <= length <= 40")
    private String isbn;
    @NotEmpty(message = "Title must not be null!")
    private String title;
    private String subtitle;
    @NotEmpty(message = "CopyrightYear should not be empty or blank!")
    private String copyrightYear;
    private String status = PublicationStatus.None.value();

    public BookDTO() {}

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
