package ua.training.service.impl;

import ua.training.dao.BeverageOrderDao;
import ua.training.dao.OrderDao;
import ua.training.dao.OrderStatusDao;
import ua.training.dao.UserDao;
import ua.training.dao.factory.DaoFactory;
import ua.training.dao.factory.DataSourceFactory;
import ua.training.entity.Order;
import ua.training.entity.OrderStatus;
import ua.training.entity.User;
import ua.training.service.OrderService;
import ua.training.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.Optional;

public class OrderServiceImpl implements OrderService {
    private static final String X_AUTH_TOKEN = "X-Auth-Token";
    private static final String BASKET = "basket";
    private static final String ADDRESS = "address";
    private static final String ON_PROCESSING_STATUS = "on_processing";

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

    private static class OrderServiceImplHolder {
        private static OrderServiceImpl instance = new OrderServiceImpl();
    }

    public static OrderServiceImpl getInstance() {
        return OrderServiceImplHolder.instance;
    }

    @Override
    public void createOrder(HttpServletRequest request) {
        Long userId = (Long) request.getSession().getAttribute(X_AUTH_TOKEN);
        String address = (String) request.getSession().getAttribute(ADDRESS);
        Map<Long, Integer> basket = (Map<Long, Integer>) request.getSession().getAttribute(BASKET);
        DataSource dataSource = DataSourceFactory.getInstance().getDataSource();
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            DaoFactory daoFactory = DaoFactory.getDaoFactory(connection);
            OrderDao orderDao = daoFactory.createOrderDao();
            OrderStatusDao orderStatusDao = daoFactory.createOrderStatusDao();
            BeverageOrderDao beverageOrderDao = daoFactory.createBeverageOrderDao();
            UserDao userDao = daoFactory.createUserDao();
            Optional<User> user = userDao.findOne(userId);
            Optional<OrderStatus> status = orderStatusDao.findByName(ON_PROCESSING_STATUS);
            orderDao.save(
                    new Order.OrderBuilder()
                            .setUser(user.get())
                            .setAddress(address)
                            .setVan(null)
                            .setStatus(status.get())
                            .build()
            );

        } catch (SQLException e) {

        }
    }


    private void addToBeverageOrder() {

    }
}
