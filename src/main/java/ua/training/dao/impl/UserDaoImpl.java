package ua.training.dao.impl;

import ua.training.dao.AbstractDao;
import ua.training.dao.UserDao;
import ua.training.dao.util.QueryBuilder;
import ua.training.entity.User;
import ua.training.entity.proxy.UserProxy;

import java.sql.*;
import java.util.List;

import static ua.training.util.constant.table.UserConstants.*;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    private UserDaoImpl(Connection connection) {
        super(TABLE, connection);
    }

    @Override
    public User findByEmail(String email) throws SQLException {
        String query = new QueryBuilder()
                .selectAll()
                .from()
                .table(TABLE)
                .where()
                .condition(TABLE, EMAIL)
                .build();
        return getEntityByQuery(query, email);
    }

    @Override
    public void setUserRole(Long userId, Long roleId) {
        String query = new QueryBuilder()
                .insert()
                .into()
                .table(USER_ROLE_TABLE)
                .insertValues(new String[]{USER_COLUMN, ROLE_COLUMN})
                .build();
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, userId);
            statement.setLong(2, roleId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> findByRole(Long roleId) throws SQLException {
        String query = "SELECT u.* FROM `user_role` ur \n" +
                "inner join `user` u ON ur.user = u.id\n" +
                "where ur.role = ?";
        return getEntityListByQuery(query, roleId);
    }

    private static class UserDaoImplHolder {
        private static UserDaoImpl instance(Connection connection) {
            return new UserDaoImpl(connection);
        }
    }

    public static UserDaoImpl getInstance(Connection connection) {
        return UserDaoImplHolder.instance(connection);
    }

    @Override
    protected String[] getParameterNames() {
        return new String[]{EMAIL, PASSWORD, FIRST_NAME, LAST_NAME, PHONE_NUMBER};
    }

    @Override
    protected void setEntityParameters(User user, PreparedStatement statement) throws SQLException {
        statement.setString(1, user.getEmail());
        statement.setString(2, user.getPassword());
        statement.setString(3, user.getFirstName());
        statement.setString(4, user.getLastName());
        statement.setString(5, user.getPhoneNumber());
    }

    @Override
    protected User getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        Long id = Long.valueOf(resultSet.getString(ID));
        String email = resultSet.getString(EMAIL);
        String password = resultSet.getString(PASSWORD);
        String firstName = resultSet.getString(FIRST_NAME);
        String lastName = resultSet.getString(LAST_NAME);
        String phoneNumber = resultSet.getString(PHONE_NUMBER);
        return new UserProxy.UserBuilder()
                .setId(id)
                .setEmail(email)
                .setPassword(password)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setPhoneNumber(phoneNumber)
                .buildUserProxy();
    }
}
