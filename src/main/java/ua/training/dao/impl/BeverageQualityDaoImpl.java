package ua.training.dao.impl;

import ua.training.dao.AbstractDao;
import ua.training.dao.BeverageQualityDao;
import ua.training.dao.BeverageTypeDao;
import ua.training.dao.util.QueryBuilder;
import ua.training.entity.BeverageQuality;
import ua.training.entity.BeverageState;
import ua.training.entity.BeverageType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class BeverageQualityDaoImpl extends AbstractDao<BeverageQuality> implements BeverageQualityDao {
    private static final String TABLE_NAME = "beverage_quality";
    private static final String ID = "id";
    private static final String NAME = "name";

    private BeverageQualityDaoImpl(Connection connection) {
        super(TABLE_NAME, connection);
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
        return new BeverageQuality.BeverageQualityBuilder()
                        .setId(id)
                        .setName(name)
                        .build();
    }
}
