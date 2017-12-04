package ua.training.entity.proxy;

import ua.training.dao.VanDao;
import ua.training.dao.factory.DaoFactory;
import ua.training.dao.factory.DataSourceFactory;
import ua.training.entity.Van;
import ua.training.entity.VanStatus;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class VanStatusProxy extends VanStatus {
    @Override
    public List<Van> getVans() {
        DataSource dataSource = DataSourceFactory.getInstance().getDataSource();
        try(Connection connection = dataSource.getConnection()) {
            VanDao vanDao = DaoFactory.getDaoFactory(connection).createVanDao();
            return vanDao.findByStatus(getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
