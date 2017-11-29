package ua.training.dao.impl;

import ua.training.dao.AbstractDao;
import ua.training.dao.UserDao;
import ua.training.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {
    private static final String TABLE_NAME = "user";
    private static final String ID = "id";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String PHONE_NUMBER = "phone_number";
    private static final int NUMBER_OF_FIELDS_WITHOUT_ID = 5;

    private UserDaoImpl(Connection connection) {
        super(TABLE_NAME, connection);
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
    protected Optional<User> getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        Long id = Long.valueOf(resultSet.getString(ID));
        String email = resultSet.getString(EMAIL);
        String password = resultSet.getString(PASSWORD);
        String firstName = resultSet.getString(FIRST_NAME);
        String lastName = resultSet.getString(LAST_NAME);
        String phoneNumber = resultSet.getString(PHONE_NUMBER);
        return Optional.of(
                new User.UserBuilder()
                        .setId(id)
                        .setEmail(email)
                        .setPassword(password)
                        .setFirstName(firstName)
                        .setLastName(lastName)
                        .setPhoneNumber(phoneNumber)
                        .build()
        );
    }

}
