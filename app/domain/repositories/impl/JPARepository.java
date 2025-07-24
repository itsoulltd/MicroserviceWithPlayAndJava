package domain.repositories.impl;

import domain.repositories.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public abstract class JPARepository<ID, Entity> implements Repository<ID, Entity> {

    private static Logger LOG = LoggerFactory.getLogger(JPARepository.class);
    public abstract Object getJpaApi();

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
