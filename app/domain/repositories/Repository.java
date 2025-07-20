package domain.repositories;

import com.it.soul.lab.data.base.DataSource;

import java.util.List;
import java.util.Optional;

public interface Repository<ID, Entity> {
    DataSource<ID, Entity> getDatasource();
    Optional<Entity> findById(ID id);
    List<Entity> findAll(int page, int limit);
    Optional<Entity> save(Entity entity);
    Optional<Entity> update(Entity entity);
    boolean delete(ID id);

    default int getOffset(int page, int limit) {
        if (limit <= 0) limit = 10;
        if (page <= 0) page = 1;
        int offset = (page - 1) * limit;
        return offset;
    }
}
