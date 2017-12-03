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
