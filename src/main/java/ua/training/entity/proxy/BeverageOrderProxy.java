package ua.training.entity.proxy;

import ua.training.dao.factory.DaoFactory;
import ua.training.dao.factory.DataSourceFactory;
import ua.training.dao.util.QueryBuilder;
import ua.training.entity.Beverage;
import ua.training.entity.BeverageOrder;
import ua.training.entity.Order;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static ua.training.util.constant.table.BeverageOrderConstants.*;

public class BeverageOrderProxy extends BeverageOrder {
    @Override
    public Beverage getBeverage() {
        if (super.getBeverage() == null) {
            String query = new QueryBuilder()
                    .select(BEVERAGE)
                    .from()
                    .table(TABLE)
                    .where()
                    .condition(TABLE, ID)
                    .build();
            DataSource dataSource = DataSourceFactory.getInstance().getDataSource();
            try (Connection connection = dataSource.getConnection()) {
                return DaoFactory
                        .getDaoFactory(connection)
                        .createBeverageDao()
                        .findOne(getIdDesiredColumnByBeverageId(query, connection));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }
        return super.getBeverage();
    }

    @Override
    public Order getOrder() {
        if (super.getOrder() == null) {
            String query = new QueryBuilder()
                    .select(ORDER)
                    .from()
                    .table(TABLE)
                    .where()
                    .condition(TABLE, ID)
                    .build();
            DataSource dataSource = DataSourceFactory.getInstance().getDataSource();
            try (Connection connection = dataSource.getConnection()) {
                return DaoFactory
                        .getDaoFactory(connection)
                        .createOrderDao()
                        .findOne(getIdDesiredColumnByBeverageId(query, connection));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }
        return super.getOrder();
    }

    private Long getIdDesiredColumnByBeverageId(String query, Connection connection) {
        Long id = null;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, getId());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    id = resultSet.getLong(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

}
