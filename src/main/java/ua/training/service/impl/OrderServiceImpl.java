package ua.training.service.impl;

import ua.training.dao.*;
import ua.training.dao.factory.DaoFactory;
import ua.training.dao.factory.DataSourceFactory;
import ua.training.entity.*;
import ua.training.exception.LoginAlreadyExistsException;
import ua.training.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {
    private static final String X_AUTH_TOKEN = "X-Auth-Token";
    private static final String BASKET = "basket";
    private static final String ADDRESS = "address";
    private static final String IN_PROCESSING_STATUS = "IN_PROCESSING";
    private static final String ON_THE_ROAD_STATUS = "ON_THE_ROAD";

//    private UserService userService;
//
//    private OrderServiceImpl(UserService userService) {
//        this.userService = userService;
//    }
//
//    private static class OrderServiceImplHolder {
//        private static OrderServiceImpl instance(UserService userService) {
//            return new OrderServiceImpl(userService);
//        }
//    }
//
//    public static OrderServiceImpl getInstance(UserService userService) {
//        return OrderServiceImplHolder.instance(userService);
//    }

    private OrderServiceImpl(){}

    private static class OrderServiceImplHolder {
        private static OrderServiceImpl instance = new OrderServiceImpl();
    }

    public static OrderServiceImpl getInstance() {
        return OrderServiceImplHolder.instance;
    }

    @Override
    public void createOrder(HttpServletRequest request) {
        Long userId = (Long) request.getSession().getAttribute(X_AUTH_TOKEN);
        String address = (String) request.getParameter(ADDRESS);
        Map<Long, Integer> basket = (Map<Long, Integer>) request.getSession().getAttribute(BASKET);
        DataSource dataSource = DataSourceFactory.getInstance().getDataSource();
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            DaoFactory daoFactory = DaoFactory.getDaoFactory(connection);
            OrderDao orderDao = daoFactory.createOrderDao();
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
                        .build()
                );
            }
            connection.commit();
        } catch (SQLException e) {

        }
    }

    @Override
    public Order save(Order order) {
        Order savedOrder = null;
        DataSource dataSource = DataSourceFactory.getInstance().getDataSource();
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            DaoFactory daoFactory = DaoFactory.getDaoFactory(connection);
            OrderDao orderDao = daoFactory.createOrderDao();
            savedOrder = orderDao.save(order);
            connection.commit();
        }  catch (SQLIntegrityConstraintViolationException e) {
            throw new LoginAlreadyExistsException(e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return savedOrder;
    }

    @Override
    public List<Order> getAll() {
        DataSource dataSource = DataSourceFactory.getInstance().getDataSource();
        try(Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            DaoFactory daoFactory = DaoFactory.getDaoFactory(connection);
            OrderDao orderDao = daoFactory.createOrderDao();
            return orderDao.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
