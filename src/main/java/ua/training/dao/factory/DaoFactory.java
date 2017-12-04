package ua.training.dao.factory;

import ua.training.dao.*;

import java.sql.Connection;

public abstract class DaoFactory {

    public abstract BeverageDao createBeverageDao();

    public abstract BeverageOrderDao createBeverageOrderDao();

    public abstract BeverageQualityDao createBeverageQualityDao();

    public abstract BeverageStateDao createBeverageStateDao();

    public abstract BeverageTypeDao createBeverageTypeDao();

    public abstract OrderDao createOrderDao();

    public abstract OrderStatusDao createOrderStatusDao();

    public abstract RoleDao createRoleDao();

    public abstract UserDao createUserDao();

    public abstract VanDao createVanDao();

    public abstract VanStatusDao createVanStatusDao();

    public static DaoFactory getDaoFactory(Connection connection) {
        return MySqlDaoFactory.getInstance(connection);
    }
}
