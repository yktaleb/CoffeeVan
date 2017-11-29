package ua.training.dao.impl;

import ua.training.dao.AbstractDao;
import ua.training.dao.VanDao;
import ua.training.dao.VanStatusDao;
import ua.training.entity.Van;
import ua.training.entity.VanStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;

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
}
