package ua.training.service.impl;

import ua.training.dao.*;
import ua.training.dao.factory.DaoFactory;
import ua.training.dao.factory.DataSourceFactory;
import ua.training.entity.*;
import ua.training.exception.LoginAlreadyExistsException;
import ua.training.service.OrderService;
import ua.training.util.ConnectionUtil;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Map;

import static ua.training.util.constant.general.Parameters.X_AUTH_TOKEN;
import static ua.training.util.constant.service.OrderServiceConstants.*;

public class OrderServiceImpl implements OrderService {
    private OrderServiceImpl() {
    }

    private static class OrderServiceImplHolder {
        private static OrderServiceImpl instance = new OrderServiceImpl();
    }

    public static OrderServiceImpl getInstance() {
        return OrderServiceImplHolder.instance;
    }

    @Override
    public void createOrder(HttpServletRequest request) {
        Long userId = (Long) request.getSession().getAttribute(X_AUTH_TOKEN);
        String address = request.getParameter(ADDRESS);
        Map<Long, Integer> basket = (Map<Long, Integer>) request.getSession().getAttribute(BASKET);
        DataSource dataSource = DataSourceFactory.getInstance().getDataSource();
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            DaoFactory daoFactory = DaoFactory.getDaoFactory(connection);
            OrderStatusDao orderStatusDao = daoFactory.createOrderStatusDao();
            BeverageOrderDao beverageOrderDao = daoFactory.createBeverageOrderDao();
            BeverageDao beverageDao = daoFactory.createBeverageDao();
            UserDao userDao = daoFactory.createUserDao();
            User user = userDao.findOne(userId);
            OrderStatus status = orderStatusDao.findByName(IN_PROCESSING_STATUS);
            Order savedOrder = save(
                    new Order.OrderBuilder()
                            .setUser(user)
                            .setAddress(address)
                            .setVan(null)
                            .setStatus(status)
                            .buildOrder()
            );
            for (Long beverageId : basket.keySet()) {
                Beverage beverage = beverageDao.findOne(beverageId);
                beverageOrderDao.save(
                        new BeverageOrder.BeverageOrderBuilder()
                                .setOrder(savedOrder)
                                .setAmount(basket.get(beverageId))
                                .setBeverage(beverage)
                                .buildBeverageOrder()
                );
            }
            connection.commit();
        } catch (SQLException e) {
            ConnectionUtil.rollback(connection);
            e.printStackTrace();
        } finally {
            ConnectionUtil.close(connection);
        }
    }

    @Override
    public Order save(Order order) {
        Order savedOrder = null;
        DataSource dataSource = DataSourceFactory.getInstance().getDataSource();
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            DaoFactory daoFactory = DaoFactory.getDaoFactory(connection);
            OrderDao orderDao = daoFactory.createOrderDao();
            savedOrder = orderDao.save(order);
            connection.commit();
        } catch (SQLIntegrityConstraintViolationException e) {
            ConnectionUtil.rollback(connection);
            throw new LoginAlreadyExistsException(e.getMessage());
        } catch (SQLException e) {
            ConnectionUtil.rollback(connection);
            e.printStackTrace();
        } finally {
            ConnectionUtil.close(connection);
        }
        return savedOrder;
    }

    @Override
    public List<Order> getAll() {
        DataSource dataSource = DataSourceFactory.getInstance().getDataSource();
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            DaoFactory daoFactory = DaoFactory.getDaoFactory(connection);
            OrderDao orderDao = daoFactory.createOrderDao();
            return orderDao.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Order> getAll(long limit, long offset) {
        DataSource dataSource = DataSourceFactory.getInstance().getDataSource();
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            DaoFactory daoFactory = DaoFactory.getDaoFactory(connection);
            OrderDao orderDao = daoFactory.createOrderDao();
            return orderDao.findAll(limit, offset);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
