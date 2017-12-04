package ua.training.service.impl;

import ua.training.dao.BeverageDao;
import ua.training.dao.factory.DaoFactory;
import ua.training.dao.factory.DataSourceFactory;
import ua.training.entity.Beverage;
import ua.training.service.BeverageService;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class BeverageServiceImpl implements BeverageService {

    public static final String PREMIUM_QUALITY = "PREMIUM";
    public static final String MIDDLE_QUALITY = "MIDDLE";
    public static final String ECONOMY_QUALITY = "ECONOMY";

    private BeverageServiceImpl() {
    }

    private static class BeverageServiceHolder {
        private static BeverageServiceImpl instance = new BeverageServiceImpl();
    }

    public static BeverageServiceImpl getInstance() {
        return BeverageServiceHolder.instance;
    }

    @Override
    public List<Beverage> findAllBeverage() {
        List<Beverage> beverages = null;
        DataSource dataSource = DataSourceFactory.getInstance().getDataSource();
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            DaoFactory daoFactory = DaoFactory.getDaoFactory(connection);
            BeverageDao beverageDao = daoFactory.createBeverageDao();
            beverages = beverageDao.findAll();
        } catch (SQLException e) {

        }
        return beverages;
    }

    @Override
    public Optional<Beverage> findById(Long id) {
        Optional<Beverage> beverage = Optional.empty();
        DataSource dataSource = DataSourceFactory.getInstance().getDataSource();
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            DaoFactory daoFactory = DaoFactory.getDaoFactory(connection);
            BeverageDao beverageDao = daoFactory.createBeverageDao();
            beverage = beverageDao.findOne(id);
        } catch (SQLException e) {

        }
        return beverage;
    }

    @Override
    public List<Beverage> getSortedByPrice() {
        List<Beverage> beverages = null;
        DataSource dataSource = DataSourceFactory.getInstance().getDataSource();
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            DaoFactory daoFactory = DaoFactory.getDaoFactory(connection);
            BeverageDao beverageDao = daoFactory.createBeverageDao();
            beverages = beverageDao.getSortedByPrice();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return beverages;
    }

    @Override
    public List<Beverage> getSortedByQuality() {
        List<Beverage> beverages = null;
        DataSource dataSource = DataSourceFactory.getInstance().getDataSource();
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            DaoFactory daoFactory = DaoFactory.getDaoFactory(connection);
            BeverageDao beverageDao = daoFactory.createBeverageDao();
            beverages = beverageDao.getSortedByQuality();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return beverages;
    }
}
