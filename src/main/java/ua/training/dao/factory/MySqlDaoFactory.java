package ua.training.dao.factory;

import ua.training.dao.*;
import ua.training.dao.impl.*;

import java.sql.Connection;

public class MySqlDaoFactory extends DaoFactory {
    private Connection connection;

    private MySqlDaoFactory(Connection connection) {
        this.connection = connection;
    }

    @Override
    public BeverageDao createBeverageDao() {
        return BeverageDaoImpl.getInstance(connection);
    }

    @Override
    public BeverageOrderDao createBeverageOrderDao() {
        return BeverageOrderDaoImpl.getInstance(connection);
    }

    @Override
    public BeverageQualityDao createBeverageQualityDao() {
        return BeverageQualityDaoImpl.getInstance(connection);
    }

    @Override
    public BeverageStateDao createBeverageStateDao() {
        return BeverageStateDaoImpl.getInstance(connection);
    }

    @Override
    public BeverageTypeDao createBeverageTypeDao() {
        return BeverageTypeDaoImpl.getInstance(connection);
    }

    @Override
    public OrderDao createOrderDao() {
        return OrderDaoImpl.getInstance(connection);
    }

    @Override
    public OrderStatusDao createOrderStatusDao() {
        return OrderStatusDaoImpl.getInstance(connection);
    }

    @Override
    public RoleDao createRoleDao() {
        return RoleDaoImpl.getInstance(connection);
    }

    @Override
    public UserDao createUserDao() {
        return UserDaoImpl.getInstance(connection);
    }

    @Override
    public VanDao createVanDao() {
        return VanDaoImpl.getInstance(connection);
    }

    @Override
    public VanStatusDao createVanStatusDao() {
        return VanStatusDaoImpl.getInstance(connection);
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
