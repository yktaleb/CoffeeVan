package ua.training.service.impl;

import ua.training.dao.OrderDao;
import ua.training.dao.OrderStatusDao;
import ua.training.dao.VanDao;
import ua.training.dao.VanStatusDao;
import ua.training.dao.factory.DaoFactory;
import ua.training.dao.factory.DataSourceFactory;
import ua.training.entity.*;
import ua.training.exception.VanCapacityException;
import ua.training.service.AdminService;
import ua.training.service.OrderService;
import ua.training.service.VanService;
import ua.training.util.ConnectionUtil;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static ua.training.util.constant.service.AdminServiceConstants.*;

public class AdminServiceImpl implements AdminService {

    private final OrderService orderService;
    private final VanService vanService;

    private AdminServiceImpl(OrderService orderService, VanService vanService) {
        this.orderService = orderService;
        this.vanService = vanService;
    }

    private static final class VanServiceImplHolder {
        private static final AdminServiceImpl instance(OrderService orderService, VanService vanService) {
            return new AdminServiceImpl(orderService, vanService);
        }
    }

    public static AdminServiceImpl getInstance(OrderService orderService, VanService vanService) {
        return VanServiceImplHolder.instance(orderService, vanService);
    }

    @Override
    public List<Van> getFreeVans() {
        return vanService.getFreeVans();
    }

    @Override
    public List<Van> getBusyVans() {
        return vanService.getBusyVans();
    }

    @Override
    public void makeVanFree(Long vanId) {
        vanService.makeVanFree(vanId);
    }

    @Override
    public Optional<Long> getNumberOfOrders() {
        return null;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderService.getAll();
    }

    @Override
    public void setOrderVan(Long orderId, Long vanId) throws VanCapacityException {
        DataSource dataSource = DataSourceFactory.getInstance().getDataSource();
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            DaoFactory daoFactory = DaoFactory.getDaoFactory(connection);
            VanDao vanDao = daoFactory.createVanDao();
            Van van = vanDao.findOne(vanId);
            if (BUSY_STATUS.equals(van.getVanStatus().getName())) {
                return;
            }

            OrderDao orderDao = daoFactory.createOrderDao();
            OrderStatusDao orderStatusDao = daoFactory.createOrderStatusDao();
            VanStatusDao vanStatusDao = daoFactory.createVanStatusDao();

            OrderStatus onTheRoadStatus = orderStatusDao.findByName(ON_THE_ROAD_STATUS);
            VanStatus busyStatus = vanStatusDao.findByName(BUSY_STATUS);
            Order order = orderDao.findOne(orderId);

            double totalVolume = 0;
            double totalWeight = 0;
            for (BeverageOrder beverageOrder : order.getBeverageOrders()) {
                Integer amount = beverageOrder.getAmount();
                totalVolume += amount * beverageOrder.getBeverage().getVolume();
                totalWeight += amount * beverageOrder.getBeverage().getWeight();
            }
            if (van.getMaxVolume() <= totalVolume) {
                throw new VanCapacityException(van, NOT_ENOUGH_VOLUME);
            } else if (van.getCarryingCapacity() <= totalWeight) {
                throw new VanCapacityException(van, NOT_ENOUGH_CARRYING_CAPACITY);
            }
            order.setVan(van);
            order.setStatus(onTheRoadStatus);
            orderDao.update(order);
            van.setVanStatus(busyStatus);
            vanDao.update(van);
            connection.commit();
        } catch (SQLException e) {
            ConnectionUtil.rollback(connection);
            e.printStackTrace();
        } finally {
            ConnectionUtil.close(connection);
        }
    }
}
