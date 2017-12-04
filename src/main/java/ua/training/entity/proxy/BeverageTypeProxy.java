package ua.training.entity.proxy;

import ua.training.dao.BeverageDao;
import ua.training.dao.factory.DaoFactory;
import ua.training.dao.factory.DataSourceFactory;
import ua.training.entity.Beverage;
import ua.training.entity.BeverageType;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BeverageTypeProxy extends BeverageType {
    @Override
    public List<Beverage> getBeverages() {
        DataSource dataSource = DataSourceFactory.getInstance().getDataSource();
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            DaoFactory daoFactory = DaoFactory.getDaoFactory(connection);
            BeverageDao beverageDao = daoFactory.createBeverageDao();
            return beverageDao.findByType(getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
