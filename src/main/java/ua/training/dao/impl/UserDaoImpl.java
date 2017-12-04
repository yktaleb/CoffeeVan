package ua.training.dao.impl;

import ua.training.dao.AbstractDao;
import ua.training.dao.UserDao;
import ua.training.dao.util.QueryBuilder;
import ua.training.entity.User;

import java.sql.*;
import java.util.Optional;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {
    private static final String TABLE_NAME = "user";
    private static final String ID = "id";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String PHONE_NUMBER = "phone_number";
    private static final String USER_ROLE_TABLE = "user_role";
    private static final String USER_COLUMN = "user";
    private static final String ROLE_COLUMN = "role";
    private static final int NUMBER_OF_FIELDS_WITHOUT_ID = 5;

    private UserDaoImpl(Connection connection) {
        super(TABLE_NAME, connection);
    }

    @Override
    public User findByEmail(String email) {
        String query = new QueryBuilder()
                .selectAll()
                .from()
                .table(TABLE_NAME)
                .where()
                .condition(TABLE_NAME, "email")
                .built();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
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
    public void setUserRole(Long userId, Long roleId) {
        String query = new QueryBuilder()
                .insert()
                .into()
                .table(USER_ROLE_TABLE)
                .insertValues(new String[]{USER_COLUMN, ROLE_COLUMN})
                .built();
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, userId);
            statement.setLong(2, roleId);
            statement.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
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
        if (statement.getParameterMetaData().getParameterCount() == NUMBER_OF_FIELDS_WITHOUT_ID + 1) {
            statement.setLong(6, user.getId());
        }
    }

    @Override
    protected User getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        Long id = Long.valueOf(resultSet.getString(ID));
        String email = resultSet.getString(EMAIL);
        String password = resultSet.getString(PASSWORD);
        String firstName = resultSet.getString(FIRST_NAME);
        String lastName = resultSet.getString(LAST_NAME);
        String phoneNumber = resultSet.getString(PHONE_NUMBER);
        return new User.UserBuilder()
                        .setId(id)
                        .setEmail(email)
                        .setPassword(password)
                        .setFirstName(firstName)
                        .setLastName(lastName)
                        .setPhoneNumber(phoneNumber)
                        .build();
    }
}
