package domain.repositories;

import com.it.soul.lab.data.base.DataSource;
import com.it.soul.lab.data.simple.SimpleDataSource;
import domain.entities.Student;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StudentRepository {

    private DataSource<Integer, Student> students = new SimpleDataSource<Integer, Student>();

    public Optional<Student> findById(int id) {
        return Optional.ofNullable(students.read(id));
    }

    private int getOffset(int page, int limit) {
        if (page <= 0) page = 1;
        int offset = (page - 1) * limit;
        return offset;
    }

    public List<Student> findAll(int page, int limit) {
        int offset = getOffset(page, limit);
        Object[] items = students.readSync(offset, limit);
        return Stream.of(items)
                .map(itm -> (Student) itm)
                .collect(Collectors.toList());
    }

    public Optional<Student> save(Student student) {
        int id = students.size();
        student.setId(id);
        students.put(id, student);
        return Optional.ofNullable(student);
    }

    public Optional<Student> update(Student student) {
        int id = student.getId();
        if (students.containsKey(id)) {
            students.put(id, student);
            return Optional.ofNullable(student);
        }
        return null;
    }

    public boolean delete(int id) {
        return students.remove(id) != null;
    }

}
