package ua.training.dao.impl;

import ua.training.dao.AbstractDao;
import ua.training.dao.OrderDao;
import ua.training.dao.factory.MySqlDaoFactory;
import ua.training.entity.Order;
import ua.training.entity.OrderStatus;
import ua.training.entity.User;
import ua.training.entity.Van;

import java.sql.*;
import java.util.Optional;

public class OrderDaoImpl extends AbstractDao<Order> implements OrderDao {
    private static final String TABLE_NAME = "order";
    private static final String ID = "id";
    private static final String ORDER_STATUS = "order_status";
    private static final String USER = "user";
    private static final String VAN = "van";
    private static final String ADDRESS = "address";

    private OrderDaoImpl(Connection connection) {
        super(TABLE_NAME, connection);
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
    protected String[] getParameterNames() {
        return new String[]{ORDER_STATUS, USER, ADDRESS};
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
        statement.setString(3, order.getAddress());
    }

    @Override
    protected Optional<Order> getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(ID);
        long orderStatusId = resultSet.getLong(ORDER_STATUS);
        long userId = resultSet.getLong(USER);
        long vanId = resultSet.getLong(VAN);
        String address = resultSet.getString(ADDRESS);

        OrderStatus orderStatus = MySqlDaoFactory.getInstance(connection).createOrderStatusDao().findOne(orderStatusId).get();
        User user = MySqlDaoFactory.getInstance(connection).createUserDao().findOne(userId).get();
        Van van = MySqlDaoFactory.getInstance(connection).createVanDao().findOne(vanId).get();
        return Optional.of(
                new Order.OrderBuilder()
                        .setId(id)
                        .setStatus(orderStatus)
                        .setUser(user)
                        .setVan(van)
                        .setAddress(address)
                        .build()
        );
    }
}
