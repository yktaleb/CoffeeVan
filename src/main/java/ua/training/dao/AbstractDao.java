package ua.training.dao;

import ua.training.dao.util.QueryBuilder;
import ua.training.entity.Entity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static ua.training.util.constant.general.Global.ID;
import static ua.training.util.constant.general.Global.NAME;

public abstract class AbstractDao<T extends Entity<Long>> implements CrudDao<T, Long> {

    protected String tableName;
    protected Connection connection;

    public AbstractDao(String tableName, Connection connection) {
        this.tableName = tableName;
        this.connection = connection;
    }

    @Override
    public T save(T entity) throws SQLException {
        String query = new QueryBuilder()
                .insert()
                .into()
                .table(tableName)
                .insertValues(getParameterNames())
                .build();
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            setEntityParameters(entity, statement);
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                entity.setId((long) generatedKeys.getInt(1));
            }
            return entity;
        }
    }

    @Override
    public T update(T entity) throws SQLException {
        String query = new QueryBuilder()
                .update()
                .table(tableName)
                .set()
                .updateValues(getParameterNames())
                .where()
                .condition(tableName, ID)
                .build();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            setEntityParameters(entity, statement);
            statement.setLong(getParameterNames().length + 1, entity.getId());
            statement.executeUpdate();
            return entity;
        }
    }

    @Override
    public T findOne(Long id) throws SQLException {
        String query = new QueryBuilder()
                .selectAll()
                .from()
                .table(tableName)
                .where()
                .condition(tableName, ID)
                .build();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return getEntityFromResultSet(resultSet);
                }
            }
        }
        return null;
    }

    @Override
    public List<T> findAll() throws SQLException {
        List<T> result = new ArrayList<>();
        String query = new QueryBuilder()
                .selectAll()
                .from()
                .table(tableName)
                .build();
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                if (getEntityFromResultSet(resultSet) != null) {
                    result.add(getEntityFromResultSet(resultSet));
                }
            }
        }
        return result;
    }

    @Override
    public List<T> findAll(long limit, long offset) throws SQLException {
        List<T> result = new ArrayList<>();
        String query = new QueryBuilder()
                .selectAll()
                .from()
                .table(tableName)
                .limit(limit, offset)
                .build();
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                if (getEntityFromResultSet(resultSet) != null) {
                    result.add(getEntityFromResultSet(resultSet));
                }
            }
        }
        return result;
    }

    @Override
    public void delete(Long id) throws SQLException {
        String query = new QueryBuilder()
                .delete()
                .from()
                .table(tableName)
                .where()
                .condition(tableName, ID)
                .build();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        }
    }

    public T findOneByName(String value) throws SQLException {
        String query = new QueryBuilder()
                .selectAll()
                .from()
                .table(tableName)
                .where()
                .condition(tableName, NAME)
                .build();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, value);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return getEntityFromResultSet(resultSet);
                }
            }
        }
        return null;
    }

    protected abstract String[] getParameterNames();

    protected abstract void setEntityParameters(T entity, PreparedStatement statement) throws SQLException;

    protected abstract T getEntityFromResultSet(ResultSet resultSet) throws SQLException;

}
