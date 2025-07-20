package domain.repositories;

import domain.entities.Student;
import domain.repositories.impl.DSRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StudentRepository extends DSRepository<Integer, Student> {

    public List<Student> findAll(int page, int limit) {
        int offset = getOffset(page, limit);
        Object[] items = getDatasource().readSync(offset, limit);
        return Stream.of(items)
                .map(itm -> (Student) itm)
                .collect(Collectors.toList());
    }

    public Optional<Student> save(Student student) {
        int id = getDatasource().size();
        student.setId(id);
        getDatasource().put(id, student);
        return Optional.ofNullable(student);
    }

    public Optional<Student> update(Student student) {
        int id = student.getId();
        if (getDatasource().containsKey(id)) {
            getDatasource().put(id, student);
            return Optional.ofNullable(student);
        }
        return null;
    }

}
