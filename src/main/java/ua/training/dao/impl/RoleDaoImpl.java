package ua.training.dao.impl;

import ua.training.dao.AbstractDao;
import ua.training.dao.CrudDao;
import ua.training.dao.RoleDao;
import ua.training.entity.Role;
import ua.training.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class RoleDaoImpl extends AbstractDao<Role> implements RoleDao {
    public RoleDaoImpl(String tableName, Connection connection) {
        super(tableName, connection);
    }

    @Override
    protected String[] getParameterNames() {
        return new String[0];
    }

    @Override
    protected void setEntityParameters(Role entity, PreparedStatement statement) {
    }
}
