package ua.training.dao.factory;

import ua.training.dao.*;
import ua.training.dao.impl.UserDaoImpl;

import java.sql.Connection;

public class MySqlDaoFactory extends DaoFactory {
    private Connection connection;

    private MySqlDaoFactory(Connection connection) {
        this.connection = connection;
    }

    @Override
    public BeverageDao createBeverageDao() {
        return null;
    }

    @Override
    public BeverageOrderDao createBeverageOrderDao() {
        return null;
    }

    @Override
    public BeverageQualityDao createBeverageQualityDao() {
        return null;
    }

    @Override
    public BeverageStateDao createBeverageStateDao() {
        return null;
    }

    @Override
    public BeverageTypeDao createBeverageTypeDao() {
        return null;
    }

    @Override
    public OrderDao createOrderDao() {
        return null;
    }

    @Override
    public OrderStatusDao createOrderStatusDao() {
        return null;
    }

    @Override
    public RoleDao createRoleDao() {
        return null;
    }

    @Override
    public UserDao createUserDao() {
        return UserDaoImpl.getInstance(connection);
    }

    @Override
    public VanDao createVanDao() {
        return null;
    }

    @Override
    public VanStatusDao createVanStatusDao() {
        return null;
    }

    private static class MySqlDaoFactoryHolder {
        private static MySqlDaoFactory instance(Connection connection) {
            return new MySqlDaoFactory(connection);
        }
    }

    public static MySqlDaoFactory getInstance(Connection connection) {
        return MySqlDaoFactoryHolder.instance(connection);
    }

}
