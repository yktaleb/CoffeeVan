package ua.training.entity.proxy;

import ua.training.dao.BeverageOrderDao;
import ua.training.dao.factory.DaoFactory;
import ua.training.dao.factory.DataSourceFactory;
import ua.training.dao.util.QueryBuilder;
import ua.training.entity.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static ua.training.util.constant.table.BeverageConstants.*;

public class BeverageProxy extends Beverage {

    @Override
    public BeverageType getType() {
        String query = new QueryBuilder()
                .select(BEVERAGE_TYPE)
                .from()
                .table(TABLE)
                .where()
                .condition(TABLE, ID)
                .built();
        DataSource dataSource = DataSourceFactory.getInstance().getDataSource();
        try (Connection connection = dataSource.getConnection()) {
            return DaoFactory
                    .getDaoFactory(connection)
                    .createBeverageTypeDao()
                    .findOne(getIdDesiredColumnByBeverageId(query, connection));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public BeverageState getState() {
        String query = new QueryBuilder()
                .select(BEVERAGE_STATE)
                .from()
                .table(TABLE)
                .where()
                .condition(TABLE, ID)
                .built();
        DataSource dataSource = DataSourceFactory.getInstance().getDataSource();
        try (Connection connection = dataSource.getConnection()) {
            return DaoFactory
                    .getDaoFactory(connection)
                    .createBeverageStateDao()
                    .findOne(getIdDesiredColumnByBeverageId(query, connection));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public BeverageQuality getQuality() {
        String query = new QueryBuilder()
                .select(BEVERAGE_QUALITY)
                .from()
                .table(TABLE)
                .where()
                .condition(TABLE, ID)
                .built();
        DataSource dataSource = DataSourceFactory.getInstance().getDataSource();
        try (Connection connection = dataSource.getConnection()) {
            return DaoFactory
                    .getDaoFactory(connection)
                    .createBeverageQualityDao()
                    .findOne(getIdDesiredColumnByBeverageId(query, connection));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Long getIdDesiredColumnByBeverageId(String query, Connection connection) {
        Long id = null;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, getId());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    id = resultSet.getLong(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public List<BeverageOrder> getBeverageOrders() {
        DataSource dataSource = DataSourceFactory.getInstance().getDataSource();
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            DaoFactory daoFactory = DaoFactory.getDaoFactory(connection);
            BeverageOrderDao beverageOrderDao = daoFactory.createBeverageOrderDao();
            return beverageOrderDao.findByBeverage(getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
