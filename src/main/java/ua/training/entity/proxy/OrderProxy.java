package ua.training.entity.proxy;

import ua.training.dao.OrderDao;
import ua.training.dao.factory.DaoFactory;
import ua.training.dao.factory.DataSourceFactory;
import ua.training.entity.BeverageOrder;
import ua.training.entity.Entity;
import ua.training.entity.Order;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OrderProxy extends Order {

    private OrderProxy() {
    }

    private OrderProxy(Order order) {
        super(order);
    }

    private static final class OrderProxyHolder {
        private static final OrderProxy INSTANCE = new OrderProxy();
        private static final OrderProxy instance(Order order) {
            return new OrderProxy(order);
        }
    }

    public static OrderProxy getInstance() {
        return OrderProxyHolder.INSTANCE;
    }

    public static OrderProxy getInstance(Order order) {
        return OrderProxyHolder.instance(order);
    }

    @Override
    public List<BeverageOrder> getBeverageOrders() {
        DataSource dataSource = DataSourceFactory.getInstance().getDataSource();
        try(Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            DaoFactory daoFactory = DaoFactory.getDaoFactory(connection);
            OrderDao orderDao = daoFactory.createOrderDao();
            return orderDao.getBeverageOrdersByOrderId(getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



}
