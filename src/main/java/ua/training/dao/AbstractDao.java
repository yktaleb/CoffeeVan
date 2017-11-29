package ua.training.dao;

import ua.training.dao.util.QueryBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractDao<T> implements CrudDao<T, Long> {

    protected String tableName;
    protected Connection connection;

    public AbstractDao(String tableName, Connection connection) {
        this.tableName = tableName;
        this.connection = connection;
    }

    @Override
    public T save(T entity) {
//        String query = "INSERT INTO ? VALUES(?)";
//        String query = "insert into user(email, password, first_name, last_name, phone_number) values('email12', 'password', 'firtst', 'last', 'phone');";
        String query = new QueryBuilder()
                .insert()
                .into()
                .table(tableName)
                .values(new String[]{"email", "password", "first_name", "last_name", "phone_number"})
                .built();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
//            setEntityToParameters(entity, statement);
            statement.setString(1, "yktaleb");
            statement.setString(2, "sas");
            statement.setString(3, "sas");
            statement.setString(4, "sas");
            statement.setString(5, "sas");
            statement.executeUpdate();
            return entity;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public T update(T entity) {
        return null;
    }

    @Override
    public T findOne(Long aLong) {
        return null;
    }

    @Override
    public List<T> findAll() {
        return null;
    }

    @Override
    public void delete(Long aLong) {

    }

    protected abstract String[] getParameterNames();

    protected abstract void setEntityParameters(T entity, PreparedStatement statement) throws SQLException;
}
