package ua.training.dao;

import ua.training.dao.util.QueryBuilder;
import ua.training.entity.Entity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDao<T extends Entity<Long>> implements CrudDao<T, Long> {

    public static final String ID = "id";
    public static final String NAME = "name";

    protected String tableName;
    protected Connection connection;

    public AbstractDao(String tableName, Connection connection) {
        this.tableName = tableName;
        this.connection = connection;
    }

    @Override
    public T save(T entity) {
        String query = new QueryBuilder()
                .insert()
                .into()
                .table(tableName)
                .insertValues(getParameterNames())
                .built();
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            setEntityParameters(entity, statement);
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                entity.setId((long) generatedKeys.getInt(1));
            }
            return entity;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public T update(T entity) {
        String query = new QueryBuilder()
                .update()
                .table(tableName)
                .set()
                .updateValues(getParameterNames())
                .where()
                .condition(tableName, ID)
                .built();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            setEntityParameters(entity, statement);
            statement.setLong(getParameterNames().length + 1, entity.getId());
            statement.executeUpdate();
            connection.commit();
            return entity;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public T findOne(Long id) {
        String query = new QueryBuilder()
                .selectAll()
                .from()
                .table(tableName)
                .where()
                .condition(tableName, ID)
                .built();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return getEntityFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {

        }
        return null;
    }

    @Override
    public List<T> findAll() {
        List<T> result = new ArrayList<>();
        String query = new QueryBuilder()
                .selectAll()
                .from()
                .table(tableName)
                .built();
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                if (getEntityFromResultSet(resultSet) != null) {
                    result.add(getEntityFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {

        }
        return result;
    }

    @Override
    public void delete(Long id) {
        String query = new QueryBuilder()
                .delete()
                .from()
                .table(tableName)
                .where()
                .condition(tableName, ID)
                .built();
        try(PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public T findOneByName(String value) {
        String query = new QueryBuilder()
                .selectAll()
                .from()
                .table(tableName)
                .where()
                .condition(tableName, NAME)
                .built();
        try(PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, value);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return getEntityFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected abstract String[] getParameterNames();

    protected abstract void setEntityParameters(T entity, PreparedStatement statement) throws SQLException;

    protected abstract T getEntityFromResultSet(ResultSet resultSet) throws SQLException;

}
