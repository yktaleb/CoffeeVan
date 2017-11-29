package ua.training.dao.impl;

import ua.training.dao.AbstractDao;
import ua.training.dao.OrderStatusDao;
import ua.training.dao.VanDao;
import ua.training.entity.OrderStatus;
import ua.training.entity.Van;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class OrderStatusDaoImpl extends AbstractDao<OrderStatus> implements OrderStatusDao {
    public OrderStatusDaoImpl(String tableName, Connection connection) {
        super(tableName, connection);
    }

    @Override
    protected String[] getParameterNames() {
        return new String[0];
    }

    @Override
    protected void setEntityParameters(OrderStatus entity, PreparedStatement statement) {
    }

    @Override
    protected Optional<OrderStatus> getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        return null;
    }
}
