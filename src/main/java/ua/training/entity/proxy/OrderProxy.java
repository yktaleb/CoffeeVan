package ua.training.entity.proxy;

import ua.training.dao.OrderDao;
import ua.training.dao.factory.DaoFactory;
import ua.training.dao.factory.DataSourceFactory;
import ua.training.dao.util.QueryBuilder;
import ua.training.entity.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static ua.training.util.constant.table.OrderConstants.*;

public class OrderProxy extends Order {

    @Override
    public User getUser() {
        if (super.getUser() == null) {
            String query = new QueryBuilder()
                    .select(USER)
                    .from()
                    .table(TABLE)
                    .where()
                    .condition(TABLE, ID)
                    .build();
            DataSource dataSource = DataSourceFactory.getInstance().getDataSource();
            try (Connection connection = dataSource.getConnection()) {
                return DaoFactory
                        .getDaoFactory(connection)
                        .createUserDao()
                        .findOne(getIdDesiredColumnByBeverageId(query, connection));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }
        return super.getUser();
    }

    @Override
    public Van getVan() {
        if (super.getVan() == null) {
            String query = new QueryBuilder()
                    .select(VAN)
                    .from()
                    .table(TABLE)
                    .where()
                    .condition(TABLE, ID)
                    .build();
            DataSource dataSource = DataSourceFactory.getInstance().getDataSource();
            try (Connection connection = dataSource.getConnection()) {
                return DaoFactory
                        .getDaoFactory(connection)
                        .createVanDao()
                        .findOne(getIdDesiredColumnByBeverageId(query, connection));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }
        return super.getVan();
    }

    @Override
    public OrderStatus getStatus() {
        if (super.getStatus() == null) {
            String query = new QueryBuilder()
                    .select(ORDER_STATUS)
                    .from()
                    .table(TABLE)
                    .where()
                    .condition(TABLE, ID)
                    .build();
            DataSource dataSource = DataSourceFactory.getInstance().getDataSource();
            try (Connection connection = dataSource.getConnection()) {
                return DaoFactory
                        .getDaoFactory(connection)
                        .createOrderStatusDao()
                        .findOne(getIdDesiredColumnByBeverageId(query, connection));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }
        return super.getStatus();
    }

    private Long getIdDesiredColumnByBeverageId(String query, Connection connection) {
        Long id = null;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, getId());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    id = resultSet.getLong(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public List<BeverageOrder> getBeverageOrders() {
        if (super.getBeverageOrders() == null) {
            DataSource dataSource = DataSourceFactory.getInstance().getDataSource();
            try (Connection connection = dataSource.getConnection()) {
                connection.setAutoCommit(false);
                DaoFactory daoFactory = DaoFactory.getDaoFactory(connection);
                OrderDao orderDao = daoFactory.createOrderDao();
                return orderDao.getBeverageOrdersByOrderId(getId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }
        return super.getBeverageOrders();
    }

}
