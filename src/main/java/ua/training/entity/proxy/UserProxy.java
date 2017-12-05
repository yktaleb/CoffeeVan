package ua.training.entity.proxy;

import ua.training.dao.OrderDao;
import ua.training.dao.RoleDao;
import ua.training.dao.factory.DaoFactory;
import ua.training.dao.factory.DataSourceFactory;
import ua.training.entity.Order;
import ua.training.entity.Role;
import ua.training.entity.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserProxy extends User {
    @Override
    public List<Role> getRoles() {
        if (super.getRoles() == null) {
            DataSource dataSource = DataSourceFactory.getInstance().getDataSource();
            try (Connection connection = dataSource.getConnection()) {
                DaoFactory daoFactory = DaoFactory.getDaoFactory(connection);
                RoleDao roleDao = daoFactory.createRoleDao();
                return roleDao.findByUser(getId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }
        return super.getRoles();
    }

    @Override
    public List<Order> getOrders() {
        if (super.getOrders() == null) {
            DataSource dataSource = DataSourceFactory.getInstance().getDataSource();
            try (Connection connection = dataSource.getConnection()) {
                DaoFactory daoFactory = DaoFactory.getDaoFactory(connection);
                OrderDao orderDao = daoFactory.createOrderDao();
                return orderDao.findByUser(getId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }
        return super.getOrders();
    }
}
