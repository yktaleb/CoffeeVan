package ua.training.dao.impl;

import ua.training.dao.AbstractDao;
import ua.training.dao.BeverageOrderDao;
import ua.training.dao.OrderDao;
import ua.training.dao.factory.DaoFactory;
import ua.training.dao.factory.DataSourceFactory;
import ua.training.dao.factory.MySqlDaoFactory;
import ua.training.dao.util.QueryBuilder;
import ua.training.entity.*;
import ua.training.entity.proxy.OrderProxy;
import ua.training.entity.proxy.ProxyFactory;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
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
                .table(TABLE_NAME)
                .where()
                .condition(TABLE_NAME, ORDER_STATUS)
                .built();
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
