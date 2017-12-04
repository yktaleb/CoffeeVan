package ua.training.dao.impl;

import ua.training.dao.AbstractDao;
import ua.training.dao.BeverageQualityDao;
import ua.training.entity.BeverageQuality;
import ua.training.entity.proxy.BeverageQualityProxy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static ua.training.util.constant.table.BeverageQualityConstants.TABLE;

public class BeverageQualityDaoImpl extends AbstractDao<BeverageQuality> implements BeverageQualityDao {


    private BeverageQualityDaoImpl(Connection connection) {
        super(TABLE, connection);
    }

    @Override
    public BeverageQuality findByName(String value) {
        return findOneByName(value);
    }

    private static class BeverageQualityDaoImplHolder {
        public static BeverageQualityDaoImpl instance(Connection connection) {
            return new BeverageQualityDaoImpl(connection);
        }
    }

    public static BeverageQualityDaoImpl getInstance(Connection connection) {
        return BeverageQualityDaoImplHolder.instance(connection);
    }

    @Override
    protected String[] getParameterNames() {
        return new String[]{NAME};
    }

    @Override
    protected void setEntityParameters(BeverageQuality beverageQuality, PreparedStatement statement) throws SQLException {
        statement.setString(1, beverageQuality.getName());
    }

    @Override
    protected BeverageQuality getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(ID);
        String name = resultSet.getString(NAME);
        return new BeverageQualityProxy.BeverageQualityBuilder()
                        .setId(id)
                        .setName(name)
                        .buildBeverageQualityProxy();
    }
}
