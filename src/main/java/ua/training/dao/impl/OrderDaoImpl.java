package ua.training.dao.impl;

import ua.training.dao.AbstractDao;
import ua.training.dao.BeverageOrderDao;
import ua.training.dao.OrderDao;
import ua.training.dao.factory.DaoFactory;
import ua.training.dao.factory.DataSourceFactory;
import ua.training.dao.util.QueryBuilder;
import ua.training.entity.*;
import ua.training.entity.proxy.OrderProxy;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ua.training.util.constant.table.OrderConstants.*;

public class OrderDaoImpl extends AbstractDao<Order> implements OrderDao {

    private OrderDaoImpl(Connection connection) {
        super(TABLE, connection);
    }

    private static final class OrderDaoImplHolder {
        private static OrderDaoImpl instance(Connection connection) {
            return new OrderDaoImpl(connection);
        }
    }

    public static OrderDaoImpl getInstance(Connection connection) {
        return OrderDaoImplHolder.instance(connection);
    }

    @Override
    public List<BeverageOrder> getBeverageOrdersByOrderId(Long id) {
        DataSource dataSource = DataSourceFactory.getInstance().getDataSource();
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            DaoFactory daoFactory = DaoFactory.getDaoFactory(connection);
            BeverageOrderDao beverageOrderDao = daoFactory.createBeverageOrderDao();
            return beverageOrderDao.findByOrder(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Order> findByStatus(Long statusId) {
        List<Order> result = new ArrayList<>();
        String query = new QueryBuilder()
                .selectAll()
                .from()
                .table(TABLE)
                .where()
                .condition(TABLE, ORDER_STATUS)
                .build();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, statusId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                if (getEntityFromResultSet(resultSet) != null) {
                    result.add(getEntityFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {

        }
        return result;
    }

    @Override
    public List<Order> findByUser(Long userId) {
        List<Order> result = new ArrayList<>();
        String query = new QueryBuilder()
                .selectAll()
                .from()
                .table(TABLE)
                .where()
                .condition(TABLE, USER)
                .build();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                if (getEntityFromResultSet(resultSet) != null) {
                    result.add(getEntityFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {

        }
        return result;
    }

    @Override
    public List<Order> findByVan(Long vanId) {
        List<Order> result = new ArrayList<>();
        String query = new QueryBuilder()
                .selectAll()
                .from()
                .table(TABLE)
                .where()
                .condition(TABLE, VAN)
                .build();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, vanId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                if (getEntityFromResultSet(resultSet) != null) {
                    result.add(getEntityFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {

        }
        return result;
    }

    @Override
    public Optional<Long> numberOfRows() {
    }

    @Override
    protected String[] getParameterNames() {
        return new String[]{ORDER_STATUS, USER, VAN, ADDRESS};
    }

    @Override
    protected void setEntityParameters(Order order, PreparedStatement statement) throws SQLException {
        statement.setLong(1, order.getStatus().getId());
        statement.setLong(2, order.getUser().getId());
        if (order.getVan() != null) {
            statement.setLong(3, order.getVan().getId());
        } else {
            statement.setNull(3, Types.INTEGER);
        }
        statement.setString(4, order.getAddress());
    }

    @Override
    protected Order getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(ID);
        String address = resultSet.getString(ADDRESS);
        return new OrderProxy.OrderBuilder()
                .setId(id)
                .setAddress(address)
                .buildOrderProxy();
    }
}
