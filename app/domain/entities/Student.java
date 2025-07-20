package domain.entities;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;

public class Student {
    @NotEmpty(message = "firstName must not be null or empty!")
    private String firstName;
    @NotEmpty(message = "lastName must not be null or empty!")
    private String lastName;
    @Min(value = 18, message = "Age min value is 18.")
    private int age;
    private int id;

    public Student() {}

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
