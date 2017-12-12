package ua.training.dao.impl;

import ua.training.dao.AbstractDao;
import ua.training.dao.RoleDao;
import ua.training.dao.util.QueryBuilder;
import ua.training.entity.Role;
import ua.training.entity.proxy.RoleProxy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static ua.training.util.constant.table.RoleConstants.*;

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
    public Role findByName(String value) throws SQLException {
        String query = new QueryBuilder()
                .selectAll()
                .from()
                .table(TABLE)
                .where()
                .condition(TABLE, NAME)
                .build();
        return getEntityByQuery(query, value);
    }

    @Override
    public List<Role> findByUser(Long userId) throws SQLException {
        String query = "SELECT r.* FROM `user_role` ur " +
                "inner join `role` r ON ur.role = r.id " +
                "where ur.user = ?";
        return getEntityListByQuery(query, userId);
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
