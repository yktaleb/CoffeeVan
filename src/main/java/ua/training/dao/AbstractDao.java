package ua.training.dao;

import ua.training.dao.util.QueryBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDao<T> implements CrudDao<T, Long> {

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
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            setEntityParameters(entity, statement);
            statement.executeUpdate();
            return entity;
        } catch (SQLException e) {
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
                .condition(tableName, "id")
                .built();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            setEntityParameters(entity, statement);
            statement.executeUpdate();
            return entity;
        } catch (SQLException e) {

        }
        return null;
    }

    @Override
    public Optional<T> findOne(Long id) {
        String query = new QueryBuilder()
                .select()
                .from()
                .table(tableName)
                .where()
                .condition(tableName, "id")
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
        return Optional.empty();
    }

    @Override
    public List<T> findAll() {
        List<T> result = new ArrayList<>();
        String query = new QueryBuilder()
                .select()
                .from()
                .table(tableName)
                .built();
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                if (getEntityFromResultSet(resultSet).isPresent()) {
                    result.add(getEntityFromResultSet(resultSet).get());
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
                .condition(tableName, "id")
                .built();
        try(PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Optional<T> findOneByName(String value) {
        String query = new QueryBuilder()
                .select()
                .from()
                .table(tableName)
                .where()
                .condition(tableName, "name")
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
        return Optional.empty();
    }

    protected abstract String[] getParameterNames();

    protected abstract void setEntityParameters(T entity, PreparedStatement statement) throws SQLException;

    protected abstract Optional<T> getEntityFromResultSet(ResultSet resultSet) throws SQLException;

}
