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
    private static final String TABLE_NAME = "beverage_state";
    private static final String ID = "id";
    private static final String NAME = "name";

    private BeverageStateDaoImpl(Connection connection) {
        super(TABLE_NAME, connection);
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
    protected Optional<BeverageState> getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(ID);
        String name = resultSet.getString(NAME);
        return Optional.of(
                new BeverageState.BeverageStateBuilder()
                        .setId(id)
                        .setName(name)
                        .build()
        );
    }
}
