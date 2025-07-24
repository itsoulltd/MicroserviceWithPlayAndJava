package domain.repositories.impl;

import com.it.soul.lab.data.base.DataSource;
import domain.repositories.Repository;

import java.util.List;
import java.util.Optional;

public abstract class InMemRepository<ID, Entity> implements Repository<ID, Entity> {

    public abstract DataSource<ID, Entity> getDatasource();

    @Override
    public long count() {
        return getDatasource().size();
    }

    public Optional<Entity> findById(ID id) {
        return Optional.ofNullable(getDatasource().read(id));
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
        return getDatasource().remove(id) != null;
    }


}
