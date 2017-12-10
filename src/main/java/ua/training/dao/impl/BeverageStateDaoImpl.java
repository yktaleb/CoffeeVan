package ua.training.dao.impl;

import ua.training.dao.AbstractDao;
import ua.training.dao.BeverageStateDao;
import ua.training.entity.BeverageState;
import ua.training.entity.proxy.BeverageStateProxy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static ua.training.util.constant.table.BeverageStateConstants.ID;
import static ua.training.util.constant.table.BeverageStateConstants.NAME;
import static ua.training.util.constant.table.BeverageStateConstants.TABLE;

public class BeverageStateDaoImpl extends AbstractDao<BeverageState> implements BeverageStateDao {

    private BeverageStateDaoImpl(Connection connection) {
        super(TABLE, connection);
    }

    @Override
    public BeverageState findByName(String value) throws SQLException {
        return findOneByName(value);
    }

    private static class BeverageStateDaoImplHolder {
        public static BeverageStateDaoImpl instance(Connection connection) {
            return new BeverageStateDaoImpl(connection);
        }
    }

    public static BeverageStateDaoImpl getInstance(Connection connection) {
        return BeverageStateDaoImplHolder.instance(connection);
    }

    @Override
    protected String[] getParameterNames() {
        return new String[]{NAME};
    }

    @Override
    protected void setEntityParameters(BeverageState beverageState, PreparedStatement statement) throws SQLException {
        statement.setString(1, beverageState.getName());
    }

    @Override
    protected BeverageState getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(ID);
        String name = resultSet.getString(NAME);
        return new BeverageStateProxy.BeverageStateBuilder()
                        .setId(id)
                        .setName(name)
                        .buildBeverageStateProxy();
    }
}
