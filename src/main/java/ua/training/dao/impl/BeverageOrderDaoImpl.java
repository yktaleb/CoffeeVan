package ua.training.dao.impl;

import ua.training.dao.AbstractDao;
import ua.training.dao.BeverageOrderDao;
import ua.training.dao.BeverageQualityDao;
import ua.training.dao.factory.MySqlDaoFactory;
import ua.training.entity.Beverage;
import ua.training.entity.BeverageOrder;
import ua.training.entity.BeverageQuality;
import ua.training.entity.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class BeverageOrderDaoImpl extends AbstractDao<BeverageOrder> implements BeverageOrderDao {
    private static final String TABLE_NAME = "beverage_order";
    private static final String ID = "id";
    private static final String ORDER = "order";
    private static final String BEVERAGE = "beverage";
    private static final String AMOUNT = "amount";

    private BeverageOrderDaoImpl(Connection connection) {
        super(TABLE_NAME, connection);
    }

    private static final class BeverageOrderDaoImplHolder {
        private static BeverageOrderDaoImpl instance(Connection connection) {
            return new BeverageOrderDaoImpl(connection);
        }
    }

    public static BeverageOrderDaoImpl getInstance(Connection connection) {
        return BeverageOrderDaoImplHolder.instance(connection);
    }

    @Override
    protected String[] getParameterNames() {
        return new String[]{ORDER, BEVERAGE, AMOUNT};
    }

    @Override
    protected void setEntityParameters(BeverageOrder beverageOrder, PreparedStatement statement) throws SQLException {
        statement.setLong(1, beverageOrder.getOrder().getId());
        statement.setLong(2, beverageOrder.getBeverage().getId());
        statement.setLong(3, beverageOrder.getAmount());
    }

    @Override
    protected Optional<BeverageOrder> getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(ID);
        long orderId = resultSet.getLong(ORDER);
        long beverageId = resultSet.getLong(BEVERAGE);
        int amount = resultSet.getInt(AMOUNT);
        Order order = MySqlDaoFactory.getInstance(connection).createOrderDao().findOne(orderId).get();
        Beverage beverage = MySqlDaoFactory.getInstance(connection).createBeverageDao().findOne(beverageId).get();
        return Optional.of(
                new BeverageOrder.BeverageOrderBuilder()
                        .setId(id)
                        .setOrder(order)
                        .setBeverage(beverage)
                        .setAmount(amount)
                        .build()
        );
    }
}
