package ua.training.service.impl;

import ua.training.dao.OrderDao;
import ua.training.dao.OrderStatusDao;
import ua.training.dao.VanDao;
import ua.training.dao.VanStatusDao;
import ua.training.dao.factory.DaoFactory;
import ua.training.dao.factory.DataSourceFactory;
import ua.training.entity.Order;
import ua.training.entity.OrderStatus;
import ua.training.entity.Van;
import ua.training.entity.VanStatus;
import ua.training.service.AdminService;
import ua.training.service.OrderService;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class AdminServiceImpl implements AdminService {

    private static final String FREE_STATUS = "FREE";
    private static final String BUSY_STATUS = "BUSY";
    private static final String IN_PROCESSING_STATUS = "IN_PROCESSING";
    private static final String ON_THE_ROAD_STATUS = "ON_THE_ROAD";

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
    public Set<Van> getBusyVans() {
        return null;
    }

    @Override
    public void setOrderVan(Long orderId, Long vanId) {
        DataSource dataSource = DataSourceFactory.getInstance().getDataSource();
        try(Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            DaoFactory daoFactory = DaoFactory.getDaoFactory(connection);
            OrderDao orderDao = daoFactory.createOrderDao();
            OrderStatusDao orderStatusDao = daoFactory.createOrderStatusDao();
            VanDao vanDao = daoFactory.createVanDao();
            VanStatusDao vanStatusDao = daoFactory.createVanStatusDao();
            OrderStatus onTheRoadStatus = orderStatusDao.findByName(ON_THE_ROAD_STATUS).get();
            VanStatus busyStatus = vanStatusDao.findByName(BUSY_STATUS).get();
            Order order = orderDao.findOne(orderId).get();
            Van van = vanDao.findOne(vanId).get();
            order.setVan(van);
            order.setStatus(onTheRoadStatus);
            orderDao.update(order);
            van.setVanStatus(busyStatus);
            vanDao.update(van);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Order> getAllOrders() {
        return orderService.getAll();
    }
}
