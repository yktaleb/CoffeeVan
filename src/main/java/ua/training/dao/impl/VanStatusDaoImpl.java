package ua.training.dao.impl;

import ua.training.dao.AbstractDao;
import ua.training.dao.VanDao;
import ua.training.dao.VanStatusDao;
import ua.training.entity.Van;
import ua.training.entity.VanStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class VanStatusDaoImpl extends AbstractDao<VanStatus> implements VanStatusDao {
    public VanStatusDaoImpl(String tableName, Connection connection) {
        super(tableName, connection);
    }

    @Override
    protected String[] getParameterNames() {
        return new String[0];
    }

    @Override
    protected void setEntityParameters(VanStatus entity, PreparedStatement statement) {
    }

    @Override
    protected Optional<VanStatus> getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        return null;
    }
}
