package domain.repositories.impl;

import com.it.soul.lab.data.base.DataSource;
import com.it.soul.lab.sql.SQLExecutor;
import com.it.soul.lab.sql.entity.Entity;
import com.it.soul.lab.sql.entity.RowMapper;
import com.it.soul.lab.sql.query.*;
import com.it.soul.lab.sql.query.models.Where;
import domain.repositories.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.db.Database;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public abstract class JDBCRepository<ID, E extends Entity> implements Repository<ID, E> {

    private static Logger LOG = LoggerFactory.getLogger(JDBCRepository.class);

    protected abstract Database getDb();
    protected abstract RowMapper<E> getMapper();
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
            List<E> items = getMapper().extract(resultSet);
            return items.size() > 0 ? Optional.ofNullable(items.get(0)) : Optional.empty();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return Optional.empty();
    }

    @Override
    public List<E> findAll(int page, int limit) {
        try (SQLExecutor executor = new SQLExecutor(getDb().getConnection())) {
            int offset = getOffset(page, limit);
            SQLQuery query = executor.createQueryBuilder(QueryType.SELECT)
                    .columns()
                    .from(getEntityType())
                    .addLimit(limit, offset)
                    .build();
            LOG.debug(query.toString());
            ResultSet resultSet = executor.executeSelect((SQLSelectQuery) query);
            List<E> items = getMapper().extract(resultSet);
            return items;
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return new ArrayList<>();
    }

    @Override
    public Optional<E> save(E entity) {
        try (SQLExecutor executor = new SQLExecutor(getDb().getConnection())) {
            //If Entity.id is auto-increment = true then Entity.id will be Updated with returned Auto-ID:
            boolean inserted = entity.insert(executor);
            LOG.info(entity.tableName() + " insertion was " + (inserted ? "successful." : "failed."));
            return Optional.of(entity);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<E> update(E entity) {
        try (SQLExecutor executor = new SQLExecutor(getDb().getConnection())) {
            //find all non-null properties.
            Map<String, Object> data = entity.marshallingToMap(true);
            String[] nonNullKeys = data.entrySet().stream()
                    .filter(entry -> entry.getValue() != null)
                    .flatMap(entry -> Stream.of(entry.getKey()))
                    .toArray(String[]::new);
            boolean updated = entity.update(executor, nonNullKeys);
            LOG.info(entity.tableName() + " update was " + (updated ? "successful." : "failed."));
            return Optional.of(entity);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return Optional.empty();
    }

    @Override
    public boolean delete(ID id) {
        try (SQLExecutor executor = new SQLExecutor(getDb().getConnection())) {
            SQLQuery query = executor.createQueryBuilder(QueryType.DELETE)
                    .from(getEntityType())
                    .where(new Where(getPrimaryKeyName()).isEqualTo(id))
                    .build();
            LOG.info(query.bindValueToString());
            int result = executor.executeDelete((SQLDeleteQuery) query);
            return result == 1;
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return false;
    }
}
