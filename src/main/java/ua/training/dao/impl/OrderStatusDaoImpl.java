package ua.training.dao.impl;

import ua.training.dao.AbstractDao;
import ua.training.dao.OrderStatusDao;
import ua.training.entity.OrderStatus;
import ua.training.entity.proxy.OrderStatusProxy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static ua.training.util.constant.table.OrderStatusConstants.ID;
import static ua.training.util.constant.table.OrderStatusConstants.NAME;
import static ua.training.util.constant.table.OrderStatusConstants.TABLE;

public class OrderStatusDaoImpl extends AbstractDao<OrderStatus> implements OrderStatusDao {
    private OrderStatusDaoImpl(Connection connection) {
        super(TABLE, connection);
    }

    @Override
    public OrderStatus findByName(String value) throws SQLException {
        return findOneByName(value);
    }

    private static final class OrderStatusDaoImplHolder {
        private static OrderStatusDaoImpl instance(Connection connection) {
            return new OrderStatusDaoImpl(connection);
        }
    }

    public static OrderStatusDaoImpl getInstance(Connection connection) {
        return OrderStatusDaoImplHolder.instance(connection);
    }

    @Override
    protected String[] getParameterNames() {
        return new String[]{NAME};
    }

    @Override
    protected void setEntityParameters(OrderStatus entity, PreparedStatement statement) throws SQLException {
        statement.setString(1, entity.getName());
    }

    @Override
    protected OrderStatus getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(ID);
        String name = resultSet.getString(NAME);
        return new OrderStatusProxy.OrderStatusBuilder()
                .setId(id)
                .setName(name)
                .buildOrderStatusProxy();
    }
}
