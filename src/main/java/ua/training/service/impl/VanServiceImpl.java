package ua.training.service.impl;

import ua.training.dao.VanDao;
import ua.training.dao.VanStatusDao;
import ua.training.dao.factory.DaoFactory;
import ua.training.dao.factory.DataSourceFactory;
import ua.training.entity.Van;
import ua.training.entity.VanStatus;
import ua.training.service.VanService;
import ua.training.util.ConnectionUtil;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static ua.training.util.constant.service.VanServiceConstants.BUSY_STATUS;
import static ua.training.util.constant.service.VanServiceConstants.FREE_STATUS;

public class VanServiceImpl implements VanService {

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
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
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
            ConnectionUtil.rollback(connection);
            e.printStackTrace();
        } finally {
            ConnectionUtil.close(connection);
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
