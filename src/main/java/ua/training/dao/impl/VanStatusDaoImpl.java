package ua.training.dao.impl;

import ua.training.dao.AbstractDao;
import ua.training.dao.VanStatusDao;
import ua.training.entity.VanStatus;
import ua.training.entity.proxy.VanStatusProxy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static ua.training.util.constant.table.VanStatusConstants.ID;
import static ua.training.util.constant.table.VanStatusConstants.NAME;
import static ua.training.util.constant.table.VanStatusConstants.TABLE;

public class VanStatusDaoImpl extends AbstractDao<VanStatus> implements VanStatusDao {

    private VanStatusDaoImpl(Connection connection) {
        super(TABLE, connection);
    }

    @Override
    public VanStatus findByName(String value) {
        return findOneByName(value);
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
    protected VanStatus getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(ID);
        String name = resultSet.getString(NAME);
        return new VanStatusProxy.VanStatusBuilder()
                        .setId(id)
                        .setName(name)
                        .buildVanStatusProxy();
    }
}
