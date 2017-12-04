package ua.training.dao.impl;

import ua.training.dao.AbstractDao;
import ua.training.dao.CrudDao;
import ua.training.dao.RoleDao;
import ua.training.dao.util.QueryBuilder;
import ua.training.entity.Role;
import ua.training.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class RoleDaoImpl extends AbstractDao<Role> implements RoleDao {
    private static final String TABLE_NAME = "role";
    private static final String USER_ROLE_TABLE = "user_role";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String USER = "user";

    private RoleDaoImpl(Connection connection) {
        super(TABLE_NAME, connection);
    }

    @Override
    public Optional<Role> findByName(String value) {
        return findOneByName(value);
    }

    @Override
    public Set<Role> findByUser(Long userId) {
        Set<Role> result = new HashSet<>();
        String query = "SELECT * FROM `user_role` ur \n" +
                "inner join `role` r ON ur.role = r.id\n" +
                "where ur.user = ?";
        try(PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    if (getEntityFromResultSet(resultSet).isPresent()) {
                        result.add(getEntityFromResultSet(resultSet).get());
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static final class RoleDaoImplHolder {
        private static RoleDaoImpl instance(Connection connection) {
            return new RoleDaoImpl(connection);
        }
    }

    public static RoleDaoImpl getInstance(Connection connection) {
        return RoleDaoImplHolder.instance(connection);
    }

    @Override
    protected String[] getParameterNames() {
        return new String[]{NAME};
    }

    @Override
    protected void setEntityParameters(Role entity, PreparedStatement statement) throws SQLException {
        statement.setString(1, entity.getName());
    }

    @Override
    protected Optional<Role> getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(ID);
        String name = resultSet.getString(NAME);
        return Optional.of(
                new Role.RoleBuilder()
                        .setId(id)
                        .setName(name)
                        .build()
        );
    }
}
