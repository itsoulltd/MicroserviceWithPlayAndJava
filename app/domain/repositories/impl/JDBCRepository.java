package domain.repositories.impl;

import com.it.soul.lab.data.base.DataSource;
import domain.repositories.Repository;
import play.db.Database;

import java.util.List;
import java.util.Optional;

public abstract class JDBCRepository<ID, Entity> implements Repository<ID, Entity> {

    @Override
    public DataSource<ID, Entity> getDatasource() {
        return null;
    }

    protected abstract Database getDb();
    public abstract Class<Entity> getEntityType();
    public abstract String getPrimaryKeyName();

    @Override
    public long count() {
        return 0;
    }

    @Override
    public Optional<Entity> findById(ID id) {
        return Optional.empty();
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

    @Override
    public boolean delete(ID id) {
        return false;
    }
}
