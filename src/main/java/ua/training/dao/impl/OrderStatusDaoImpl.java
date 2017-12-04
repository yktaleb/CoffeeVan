package ua.training.dao.impl;

import ua.training.dao.AbstractDao;
import ua.training.dao.OrderStatusDao;
import ua.training.dao.VanDao;
import ua.training.dao.util.QueryBuilder;
import ua.training.entity.OrderStatus;
import ua.training.entity.Van;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class OrderStatusDaoImpl extends AbstractDao<OrderStatus> implements OrderStatusDao {
    private static final String TABLE_NAME = "order_status";
    private static final String ID = "id";
    private static final String NAME = "name";

    private OrderStatusDaoImpl(Connection connection) {
        super(TABLE_NAME, connection);
    }

    @Override
    public OrderStatus findByName(String value) {
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
        return new OrderStatus.OrderStatusBuilder()
                        .setId(id)
                        .setName(name)
                        .build();
    }
}
