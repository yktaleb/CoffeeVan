package ua.training.service.impl;

import ua.training.dao.VanDao;
import ua.training.dao.factory.DaoFactory;
import ua.training.dao.factory.DataSourceFactory;
import ua.training.entity.Van;
import ua.training.service.VanService;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Set;

public class VanServiceImpl implements VanService {

    private static final String FREE_STATUS = "FREE";

    private VanServiceImpl(){}

    private static final class VanServiceImplHolder {
        private static final VanServiceImpl instance = new VanServiceImpl();
    }

    public static VanServiceImpl getInstance() {
        return VanServiceImplHolder.instance;
    }

    @Override
    public Set<Van> getFreeVans() {
        DataSource dataSource = DataSourceFactory.getInstance().getDataSource();
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            DaoFactory daoFactory = DaoFactory.getDaoFactory(connection);
            VanDao vanDao = daoFactory.createVanDao();
//            Optional<VanStatus> freeStatus = daoFactory.createVanStatusDao().findByName(FREE_STATUS);
            return vanDao.findAllByStatus(FREE_STATUS);
        } catch (SQLException e) {

        }
        return null;
    }
}
