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

public class BeverageServiceImpl implements BeverageService {
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
}
