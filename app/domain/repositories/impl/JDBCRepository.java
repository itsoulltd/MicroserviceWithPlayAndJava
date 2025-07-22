package domain.repositories.impl;

import com.it.soul.lab.data.base.DataSource;
import com.it.soul.lab.sql.SQLExecutor;
import com.it.soul.lab.sql.entity.Entity;
import com.it.soul.lab.sql.query.QueryType;
import com.it.soul.lab.sql.query.SQLQuery;
import com.it.soul.lab.sql.query.SQLScalarQuery;
import com.it.soul.lab.sql.query.SQLSelectQuery;
import com.it.soul.lab.sql.query.models.Table;
import com.it.soul.lab.sql.query.models.Where;
import domain.repositories.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.db.Database;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

public abstract class JDBCRepository<ID, E extends Entity> implements Repository<ID, E> {

    private static Logger LOG = LoggerFactory.getLogger(JDBCRepository.class);

    protected abstract Database getDb();
    public abstract Class<E> getEntityType();
    public abstract String getPrimaryKeyName();

    @Override
    public DataSource<ID, E> getDatasource() {
        return null;
    }

    @Override
    public long count() {
        try (SQLExecutor executor = new SQLExecutor(getDb().getConnection())) {
            SQLQuery query = executor.createQueryBuilder(QueryType.COUNT)
                    .columns()
                    .on(getEntityType())
                    .build();
            LOG.debug(query.bindValueToString());
            int count = executor.getScalarValue((SQLScalarQuery) query);
            return count;
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return 0;
    }

    @Override
    public Optional<E> findById(ID id) {
        try (SQLExecutor executor = new SQLExecutor(getDb().getConnection())) {
            SQLQuery query = executor.createQueryBuilder(QueryType.SELECT)
                    .columns()
                    .from(getEntityType())
                    .where(new Where(getPrimaryKeyName()).isEqualTo(id))
                    .build();
            LOG.debug(query.toString());
            ResultSet resultSet = executor.executeSelect((SQLSelectQuery) query);
            Table table = executor.collection(resultSet);
            List<E> items = table.inflate(getEntityType(), Entity.mapColumnsToProperties(getEntityType()));
            return Optional.ofNullable(items.get(0));
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return Optional.empty();
    }

    @Override
    public List<E> findAll(int page, int limit) {
        try (SQLExecutor executor = new SQLExecutor(getDb().getConnection())) {
            //TODO:
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public Optional<E> save(Entity entity) {
        try (SQLExecutor executor = new SQLExecutor(getDb().getConnection())) {
            //TODO:
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<E> update(Entity entity) {
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
