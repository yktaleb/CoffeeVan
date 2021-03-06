package ua.training.dao.impl;

import ua.training.dao.AbstractDao;
import ua.training.dao.BeverageOrderDao;
import ua.training.dao.OrderDao;
import ua.training.dao.factory.DaoFactory;
import ua.training.dao.factory.DataSourceFactory;
import ua.training.dao.util.QueryBuilder;
import ua.training.entity.BeverageOrder;
import ua.training.entity.Order;
import ua.training.entity.proxy.OrderProxy;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

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
    public List<BeverageOrder> getBeverageOrdersByOrderId(Long id) throws SQLException {
        DataSource dataSource = DataSourceFactory.getInstance().getDataSource();
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            DaoFactory daoFactory = DaoFactory.getDaoFactory(connection);
            BeverageOrderDao beverageOrderDao = daoFactory.createBeverageOrderDao();
            return beverageOrderDao.findByOrder(id);
        }
    }

    @Override
    public List<Order> findByStatus(Long statusId) throws SQLException {
        String query = new QueryBuilder()
                .selectAll()
                .from()
                .table(TABLE)
                .where()
                .condition(TABLE, ORDER_STATUS)
                .build();
        return getEntityListByQuery(query, statusId);
    }

    @Override
    public List<Order> findByUser(Long userId) throws SQLException {
        String query = new QueryBuilder()
                .selectAll()
                .from()
                .table(TABLE)
                .where()
                .condition(TABLE, USER)
                .build();
        return getEntityListByQuery(query, userId);
    }

    @Override
    public List<Order> findByVan(Long vanId) throws SQLException {
        String query = new QueryBuilder()
                .selectAll()
                .from()
                .table(TABLE)
                .where()
                .condition(TABLE, VAN)
                .build();
        return getEntityListByQuery(query, vanId);
    }

    @Override
    public int getNumberOfRows() throws SQLException {
        String query = new QueryBuilder()
                .select()
                .count()
                .as(COUNT)
                .from()
                .table(TABLE)
                .build();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                return resultSet.getInt(COUNT);
            }
        }
        return 0;
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
