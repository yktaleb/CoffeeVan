package ua.training.service.impl;

import ua.training.dao.VanDao;
import ua.training.dao.VanStatusDao;
import ua.training.dao.factory.DaoFactory;
import ua.training.dao.factory.DataSourceFactory;
import ua.training.entity.Order;
import ua.training.entity.Van;
import ua.training.entity.VanStatus;
import ua.training.service.AdminService;
import ua.training.service.OrderService;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public class AdminServiceImpl implements AdminService {

    private static final String FREE_STATUS = "FREE";
    private final OrderService orderService;

    private AdminServiceImpl(OrderService orderService){
        this.orderService = orderService;
    }

    private static final class VanServiceImplHolder {
        private static final AdminServiceImpl instance(OrderService orderService) {
            return new AdminServiceImpl(orderService);
        }
    }

    public static AdminServiceImpl getInstance(OrderService orderService) {
        return VanServiceImplHolder.instance(orderService);
    }

    @Override
    public Set<Van> getFreeVans() {
        DataSource dataSource = DataSourceFactory.getInstance().getDataSource();
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            DaoFactory daoFactory = DaoFactory.getDaoFactory(connection);
            VanDao vanDao = daoFactory.createVanDao();
            VanStatusDao vanStatusDao = daoFactory.createVanStatusDao();
            VanStatus vanStatus = vanStatusDao.findByName(FREE_STATUS).get();
//            Optional<VanStatus> freeStatus = daoFactory.createVanStatusDao().findByName(FREE_STATUS);
            return vanDao.findAllByStatus(vanStatus.getId());
        } catch (SQLException e) {

        }
        return null;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderService.getAll();
    }
}
