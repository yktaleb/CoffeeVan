package ua.training.dao.impl;

import ua.training.dao.AbstractDao;
import ua.training.dao.BeverageDao;
import ua.training.dao.util.QueryBuilder;
import ua.training.entity.Beverage;
import ua.training.entity.proxy.BeverageProxy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static ua.training.util.constant.table.BeverageConstants.*;

public class BeverageDaoImpl extends AbstractDao<Beverage> implements BeverageDao {

    private BeverageDaoImpl(Connection connection) {
        super(TABLE, connection);
    }

    @Override
    public List<Beverage> getSortedByPrice() {
        List<Beverage> result = new ArrayList<>();
        String query = new QueryBuilder()
                .selectAll()
                .from()
                .table(TABLE)
                .orderBy(PRICE)
                .build();
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                if (getEntityFromResultSet(resultSet) != null) {
                    result.add(getEntityFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {

        }
        return result;
    }

    @Override
    public List<Beverage> getSortedByQuality() {
        List<Beverage> result = new ArrayList<>();
        String query = new QueryBuilder()
                .selectAll()
                .from()
                .table(TABLE)
                .orderBy(BEVERAGE_QUALITY)
                .build();
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                if (getEntityFromResultSet(resultSet) != null) {
                    result.add(getEntityFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {

        }
        return result;
    }

    @Override
    public List<Beverage> findByQuality(Long qualityId) {
        List<Beverage> result = new ArrayList<>();
        String query = new QueryBuilder()
                .selectAll()
                .from()
                .table(TABLE)
                .where()
                .condition(TABLE, BEVERAGE_QUALITY)
                .build();
        return getBeverageListByQuery(query, qualityId);
    }

    @Override
    public List<Beverage> findByState(Long stateId) {
        List<Beverage> result = new ArrayList<>();
        String query = new QueryBuilder()
                .selectAll()
                .from()
                .table(TABLE)
                .where()
                .condition(TABLE, BEVERAGE_STATE)
                .build();
        return getBeverageListByQuery(query, stateId);
    }

    @Override
    public List<Beverage> findByType(Long typeId) {
        List<Beverage> result = new ArrayList<>();
        String query = new QueryBuilder()
                .selectAll()
                .from()
                .table(TABLE)
                .where()
                .condition(TABLE, BEVERAGE_TYPE)
                .build();
        return getBeverageListByQuery(query, typeId);
    }

    private List<Beverage> getBeverageListByQuery(String query, Long introducedId) {
        List<Beverage> result = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, introducedId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    result.add(getEntityFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {

        }
        return result;
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
    protected Beverage getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(ID);
        String name = resultSet.getString(NAME);
        Double price = resultSet.getDouble(PRICE);
        Double weight = resultSet.getDouble(WEIGHT);
        Double volume = resultSet.getDouble(VOLUME);

        return new BeverageProxy.BeverageBuilder()
                .setId(id)
                .setName(name)
                .setPrice(price)
                .setWeight(weight)
                .setVolume(volume)
                .buildBeverageProxy();
    }
}

