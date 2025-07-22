package domain.repositories.impl;

import com.it.soul.lab.data.base.DataSource;
import com.it.soul.lab.data.simple.SimpleDataSource;
import domain.repositories.Repository;

import java.util.List;
import java.util.Optional;

public class InMemRepository<ID, Entity> implements Repository<ID, Entity> {

    private DataSource<ID, Entity> dataSource = new SimpleDataSource<>();

    @Override
    public DataSource<ID, Entity> getDatasource() {
        return dataSource;
    }

    @Override
    public long count() {
        return dataSource.size();
    }

    public Optional<Entity> findById(ID id) {
        return Optional.ofNullable(dataSource.read(id));
    }

    @Override
    public List<Entity> findAll(int page, int limit) {
        return null;
    }

    @Override
    public Optional<Entity> save(Entity entity) {
        return Optional.empty();
    }

    @Override
    public Optional<Entity> update(Entity entity) {
        return Optional.empty();
    }

    public boolean delete(ID id) {
        return dataSource.remove(id) != null;
    }


}
