package domain.repositories;

import com.infoworks.data.base.iDataSource;
import com.infoworks.data.impl.SimpleDataSource;
import domain.entities.Student;
import domain.repositories.impl.InMemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Singleton
public class StudentRepository extends InMemRepository<Integer, Student> {

    private static Logger LOG = LoggerFactory.getLogger(StudentRepository.class);
    private iDataSource<Integer, Student> dataSource = new SimpleDataSource<>();

    @Override
    public iDataSource<Integer, Student> getDatasource() {
        return dataSource;
    }

    public List<Student> findAll(int page, int limit) {
        int offset = getOffset(page, limit);
        Object[] items = getDatasource().readSync(offset, limit);
        return Stream.of(items)
                .map(itm -> (Student) itm)
                .collect(Collectors.toList());
    }

    public Optional<Student> save(Student student) {
        int id = getDatasource().size() + 1;
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
