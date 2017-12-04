package ua.training.dao.impl;

import ua.training.dao.AbstractDao;
import ua.training.dao.BeverageTypeDao;
import ua.training.entity.BeverageType;
import ua.training.entity.proxy.BeverageTypeProxy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BeverageTypeDaoImpl extends AbstractDao<BeverageType> implements BeverageTypeDao {
    private static final String TABLE_NAME = "beverage_type";
    private static final String ID = "id";
    private static final String NAME = "name";

    private BeverageTypeDaoImpl(Connection connection) {
        super(TABLE_NAME, connection);
    }

    @Override
    public BeverageType findByName(String value) {
        return findOneByName(value);
    }

    private static class BeverageTypeDaoImplHolder {
        public static BeverageTypeDaoImpl instance(Connection connection) {
            return new BeverageTypeDaoImpl(connection);
        }
    }

    public static BeverageTypeDaoImpl getInstance(Connection connection) {
        return BeverageTypeDaoImplHolder.instance(connection);
    }

    @Override
    protected String[] getParameterNames() {
        return new String[]{NAME};
    }

    @Override
    protected void setEntityParameters(BeverageType beverageType, PreparedStatement statement) throws SQLException {
        statement.setString(1, beverageType.getName());
    }

    @Override
    protected BeverageType getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(ID);
        String name = resultSet.getString(NAME);
        return new BeverageTypeProxy.BeverageTypeBuilder()
                        .setId(id)
                        .setName(name)
                        .buildBeverageTypeProxy();
    }
}
