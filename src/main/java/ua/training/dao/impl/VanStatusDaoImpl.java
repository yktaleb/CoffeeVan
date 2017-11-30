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
    private static final String TABLE_NAME = "van_status";
    private static final String ID = "id";
    private static final String NAME = "name";

    private VanStatusDaoImpl(Connection connection) {
        super(TABLE_NAME, connection);
    }

    private static class VanStatusDaoImplHolder {
        public static VanStatusDaoImpl instance(Connection connection) {
            return new VanStatusDaoImpl(connection);
        }
    }

    public static VanStatusDaoImpl getInstance(Connection connection) {
        return VanStatusDaoImplHolder.instance(connection);
    }

    @Override
    protected String[] getParameterNames() {
        return new String[]{NAME};
    }

    @Override
    protected void setEntityParameters(VanStatus vanStatus, PreparedStatement statement) throws SQLException {
        statement.setString(1, vanStatus.getName());
    }

    @Override
    protected Optional<VanStatus> getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(ID);
        String name = resultSet.getString(NAME);
        return Optional.of(
                new VanStatus.VanStatusBuilder()
                        .setId(id)
                        .setName(name)
                        .build()
        );
    }
}
