package ua.training.dao.impl;

import ua.training.dao.AbstractDao;
import ua.training.dao.OrderDao;
import ua.training.dao.OrderStatusDao;
import ua.training.entity.Order;
import ua.training.entity.OrderStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class OrderDaoImpl extends AbstractDao<Order> implements OrderDao {
    public OrderDaoImpl(String tableName, Connection connection) {
        super(tableName, connection);
    }

    @Override
    protected String[] getParameterNames() {
        return new String[0];
    }

    @Override
    protected void setEntityParameters(Order entity, PreparedStatement statement) {
    }

    @Override
    protected Optional<Order> getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        return null;
    }
}