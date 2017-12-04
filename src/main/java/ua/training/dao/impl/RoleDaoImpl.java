package ua.training.dao.impl;

import ua.training.dao.AbstractDao;
import ua.training.dao.RoleDao;
import ua.training.entity.Role;
import ua.training.entity.proxy.RoleProxy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static ua.training.util.constant.table.RoleConstants.TABLE;

public class RoleDaoImpl extends AbstractDao<Role> implements RoleDao {

    private RoleDaoImpl(Connection connection) {
        super(TABLE, connection);
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
    public Role findByName(String value) {
        return findOneByName(value);
    }

    @Override
    public List<Role> findByUser(Long userId) {
        List<Role> result = new ArrayList<>();
        String query = "SELECT r.*` FROM `user_role` ur \n" +
                "inner join `role` r ON ur.role = r.id\n" +
                "where ur.user = ?";
        try(PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    if (getEntityFromResultSet(resultSet) != null) {
                        result.add(getEntityFromResultSet(resultSet));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
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
    protected Role getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(ID);
        String name = resultSet.getString(NAME);
        return new RoleProxy.RoleBuilder()
                        .setId(id)
                        .setName(name)
                        .buildRoleProxy();
    }
}
