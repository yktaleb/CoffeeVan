package ua.training.service.impl;

import ua.training.dao.VanDao;
import ua.training.dao.VanStatusDao;
import ua.training.dao.factory.DaoFactory;
import ua.training.dao.factory.DataSourceFactory;
import ua.training.entity.Van;
import ua.training.entity.VanStatus;
import ua.training.service.OrderService;
import ua.training.service.VanService;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class VanServiceImpl implements VanService {

    private static final String FREE_STATUS = "FREE";
    private static final String BUSY_STATUS = "BUSY";

    private VanServiceImpl() {
    }

    private static final class VanServiceImplHolder {
        private static final VanServiceImpl INSTANCE = new VanServiceImpl();

    }

    public static VanServiceImpl getInstance() {
        return VanServiceImplHolder.INSTANCE;
    }

    @Override
    public List<Van> getFreeVans() {
        return getVansByStatus(FREE_STATUS);
    }

    @Override
    public List<Van> getBusyVans() {
        return getVansByStatus(BUSY_STATUS);
    }

    @Override
    public void makeVanFree(Long vanId) {
        DataSource dataSource = DataSourceFactory.getInstance().getDataSource();
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            DaoFactory daoFactory = DaoFactory.getDaoFactory(connection);
            VanDao vanDao = daoFactory.createVanDao();
            VanStatusDao vanStatusDao = daoFactory.createVanStatusDao();
            VanStatus vanStatus = vanStatusDao.findByName(FREE_STATUS);
            Van van = vanDao.findOne(vanId);
            van.setVanStatus(vanStatus);
            vanDao.update(van);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private List<Van> getVansByStatus(String status) {
        DataSource dataSource = DataSourceFactory.getInstance().getDataSource();
        try (Connection connection = dataSource.getConnection()) {
            DaoFactory daoFactory = DaoFactory.getDaoFactory(connection);
            VanDao vanDao = daoFactory.createVanDao();
            VanStatusDao vanStatusDao = daoFactory.createVanStatusDao();
            VanStatus vanStatus = vanStatusDao.findByName(status);
            return vanDao.findByStatus(vanStatus.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
