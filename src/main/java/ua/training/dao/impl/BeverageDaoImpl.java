package ua.training.dao.impl;

import ua.training.dao.AbstractDao;
import ua.training.dao.BeverageDao;
import ua.training.dao.BeverageQualityDao;
import ua.training.dao.factory.MySqlDaoFactory;
import ua.training.entity.Beverage;
import ua.training.entity.BeverageQuality;
import ua.training.entity.BeverageState;
import ua.training.entity.BeverageType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class BeverageDaoImpl extends AbstractDao<Beverage> implements BeverageDao {
    private static final String TABLE_NAME = "beverage";
    private static final String ID = "id";
    private static final String BEVERAGE_TYPE = "beverage_type";
    private static final String BEVERAGE_STATE = "beverage_state";
    private static final String BEVERAGE_QUALITY = "beverage_quality";
    private static final String NAME = "name";
    private static final String PRICE = "price";
    private static final String WEIGHT = "weight";
    private static final String VOLUME = "volume";

    private BeverageDaoImpl(Connection connection) {
        super(TABLE_NAME, connection);
    }

    private static final class BeverageDaoImplHolder {
        private static BeverageDaoImpl instance(Connection connection) {
            return new BeverageDaoImpl(connection);
        }
    }

    public static BeverageDaoImpl getInstance(Connection connection) {
        return BeverageDaoImplHolder.instance(connection);
    }

    @Override
    protected String[] getParameterNames() {
        return new String[]{BEVERAGE_TYPE, BEVERAGE_STATE, BEVERAGE_QUALITY, NAME, PRICE, WEIGHT, VOLUME};
    }

    @Override
    protected void setEntityParameters(Beverage beverage, PreparedStatement statement) throws SQLException {
        statement.setLong(1, beverage.getType().getId());
        statement.setLong(2, beverage.getState().getId());
        statement.setLong(3, beverage.getQuality().getId());
        statement.setString(4, beverage.getName());
        statement.setDouble(5, beverage.getPrice());
        statement.setDouble(6, beverage.getWeight());
        statement.setDouble(7, beverage.getVolume());
    }

    @Override
    protected Optional<Beverage> getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(ID);
        long beverageTypeId = resultSet.getLong(BEVERAGE_TYPE);
        long beverageStateId = resultSet.getLong(BEVERAGE_STATE);
        long beverageQualityId = resultSet.getLong(BEVERAGE_QUALITY);
        String name = resultSet.getString(NAME);
        Double price = resultSet.getDouble(PRICE);
        Double weight = resultSet.getDouble(WEIGHT);
        Double volume = resultSet.getDouble(VOLUME);

        BeverageType beverageType = MySqlDaoFactory.getInstance(connection).createBeverageTypeDao().findOne(beverageTypeId).get();
        BeverageState beverageState = MySqlDaoFactory.getInstance(connection).createBeverageStateDao().findOne(beverageStateId).get();
        BeverageQuality beverageQuality = MySqlDaoFactory.getInstance(connection).createBeverageQualityDao().findOne(beverageQualityId).get();
        return Optional.of(
                new Beverage.BeverageBuilder()
                        .setId(id)
                        .setType(beverageType)
                        .setState(beverageState)
                        .setQuality(beverageQuality)
                        .setName(name)
                        .setPrice(price)
                        .setWeight(weight)
                        .setVolume(volume)
                        .build()
        );
    }
}

