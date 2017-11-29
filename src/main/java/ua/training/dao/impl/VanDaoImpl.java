package ua.training.dao.impl;

import ua.training.dao.AbstractDao;
import ua.training.dao.UserDao;
import ua.training.dao.VanDao;
import ua.training.entity.User;
import ua.training.entity.Van;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class VanDaoImpl extends AbstractDao<Van> implements VanDao {
    public VanDaoImpl(String tableName, Connection connection) {
        super(tableName, connection);
    }

    @Override
    protected String[] getParameterNames() {
        return new String[0];
    }

    @Override
    protected void setEntityParameters(Van entity, PreparedStatement statement) {
    }

    @Override
    protected Optional<Van> getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        return null;
    }
}