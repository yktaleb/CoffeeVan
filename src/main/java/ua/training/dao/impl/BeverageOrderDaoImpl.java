package ua.training.dao.impl;

import ua.training.dao.AbstractDao;
import ua.training.dao.BeverageOrderDao;
import ua.training.dao.util.QueryBuilder;
import ua.training.entity.*;
import ua.training.entity.proxy.BeverageOrderProxy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static ua.training.util.constant.table.BeverageOrderConstants.*;

public class BeverageOrderDaoImpl extends AbstractDao<BeverageOrder> implements BeverageOrderDao {
    private BeverageOrderDaoImpl(Connection connection) {
        super(TABLE, connection);
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
    public List<BeverageOrder> findByOrder(Long orderId) {
        String query = new QueryBuilder()
                .selectAll()
                .from()
                .table(TABLE)
                .where()
                .condition(TABLE, ORDER)
                .build();
        return getBeverageOrderListByQuery(query, orderId);
    }

    @Override
    public List<BeverageOrder> findByBeverage(Long beverageId) {
        String query = new QueryBuilder()
                .selectAll()
                .from()
                .table(TABLE)
                .where()
                .condition(TABLE, BEVERAGE)
                .build();
        return getBeverageOrderListByQuery(query, beverageId);
    }

    private List<BeverageOrder> getBeverageOrderListByQuery(String query, Long introducedId) {
        List<BeverageOrder> result = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, introducedId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    result.add(getEntityFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
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
    protected BeverageOrder getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(ID);
        int amount = resultSet.getInt(AMOUNT);
        return new BeverageOrderProxy.BeverageOrderBuilder()
                        .setId(id)
                        .setAmount(amount)
                        .buildBeverageOrderProxy();
    }
}
