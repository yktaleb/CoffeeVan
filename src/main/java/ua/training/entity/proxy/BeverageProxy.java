package ua.training.entity.proxy;

import ua.training.dao.BeverageOrderDao;
import ua.training.dao.factory.DaoFactory;
import ua.training.dao.factory.DataSourceFactory;
import ua.training.dao.util.QueryBuilder;
import ua.training.entity.*;
import ua.training.util.ProxyUtil;

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
        if (super.getType() == null) {
            String query = new QueryBuilder()
                    .select(BEVERAGE_TYPE)
                    .from()
                    .table(TABLE)
                    .where()
                    .condition(TABLE, ID)
                    .build();
            DataSource dataSource = DataSourceFactory.getInstance().getDataSource();
            try (Connection connection = dataSource.getConnection()) {
                return DaoFactory
                        .getDaoFactory(connection)
                        .createBeverageTypeDao()
                        .findOne(ProxyUtil.getIdDesiredColumnByQuery(connection, query, getId()));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }
        return super.getType();
    }

    @Override
    public BeverageState getState() {
        if (super.getState() == null) {
            String query = new QueryBuilder()
                    .select(BEVERAGE_STATE)
                    .from()
                    .table(TABLE)
                    .where()
                    .condition(TABLE, ID)
                    .build();
            DataSource dataSource = DataSourceFactory.getInstance().getDataSource();
            try (Connection connection = dataSource.getConnection()) {
                return DaoFactory
                        .getDaoFactory(connection)
                        .createBeverageStateDao()
                        .findOne(ProxyUtil.getIdDesiredColumnByQuery(connection, query, getId()));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }
        return super.getState();
    }

    @Override
    public BeverageQuality getQuality() {
        if (super.getQuality() == null) {
            String query = new QueryBuilder()
                    .select(BEVERAGE_QUALITY)
                    .from()
                    .table(TABLE)
                    .where()
                    .condition(TABLE, ID)
                    .build();
            DataSource dataSource = DataSourceFactory.getInstance().getDataSource();
            try (Connection connection = dataSource.getConnection()) {
                return DaoFactory
                        .getDaoFactory(connection)
                        .createBeverageQualityDao()
                        .findOne(ProxyUtil.getIdDesiredColumnByQuery(connection, query, getId()));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }
        return super.getQuality();
    }

    @Override
    public List<BeverageOrder> getBeverageOrders() {
        if (super.getBeverageOrders() == null) {
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
        return super.getBeverageOrders();
    }
}
