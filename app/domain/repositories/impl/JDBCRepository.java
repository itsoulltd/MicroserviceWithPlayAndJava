package domain.repositories.impl;

import com.it.soul.lab.data.base.DataSource;
import com.it.soul.lab.sql.SQLExecutor;
import domain.repositories.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.db.Database;

import java.util.List;
import java.util.Optional;

public abstract class JDBCRepository<ID, Entity> implements Repository<ID, Entity> {

    private static Logger LOG = LoggerFactory.getLogger(JDBCRepository.class);

    protected abstract Database getDb();
    public abstract Class<Entity> getEntityType();
    public abstract String getPrimaryKeyName();

    @Override
    public DataSource<ID, Entity> getDatasource() {
        return null;
    }

    @Override
    public long count() {
        try (SQLExecutor executor = new SQLExecutor(getDb().getConnection())) {
            //TODO:
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return 0;
    }

    @Override
    public Optional<Entity> findById(ID id) {
        try (SQLExecutor executor = new SQLExecutor(getDb().getConnection())) {
             //TODO:
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return Optional.empty();
    }

    @Override
    public List<Entity> findAll(int page, int limit) {
        try (SQLExecutor executor = new SQLExecutor(getDb().getConnection())) {
            //TODO:
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public Optional<Entity> save(Entity entity) {
        try (SQLExecutor executor = new SQLExecutor(getDb().getConnection())) {
            //TODO:
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Entity> update(Entity entity) {
        try (SQLExecutor executor = new SQLExecutor(getDb().getConnection())) {
            //TODO:
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return Optional.empty();
    }

    @Override
    public boolean delete(ID id) {
        try (SQLExecutor executor = new SQLExecutor(getDb().getConnection())) {
            //TODO:
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return false;
    }
}
