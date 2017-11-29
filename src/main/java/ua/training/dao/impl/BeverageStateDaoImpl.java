package ua.training.dao.impl;

import ua.training.dao.AbstractDao;
import ua.training.dao.BeverageStateDao;
import ua.training.dao.BeverageTypeDao;
import ua.training.entity.BeverageState;
import ua.training.entity.BeverageType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class BeverageStateDaoImpl extends AbstractDao<BeverageState> implements BeverageStateDao {
    public BeverageStateDaoImpl(String tableName, Connection connection) {
        super(tableName, connection);
    }

    @Override
    protected String[] getParameterNames() {
        return new String[0];
    }

    @Override
    protected void setEntityParameters(BeverageState entity, PreparedStatement statement) {
    }

    @Override
    protected Optional<BeverageState> getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        return null;
    }
}
