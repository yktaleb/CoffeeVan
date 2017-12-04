package ua.training.entity.proxy;

import ua.training.dao.OrderDao;
import ua.training.dao.factory.DaoFactory;
import ua.training.dao.factory.DataSourceFactory;
import ua.training.dao.util.QueryBuilder;
import ua.training.entity.Order;
import ua.training.entity.Van;
import ua.training.entity.VanStatus;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static ua.training.util.constant.table.OrderConstants.*;
import static ua.training.util.constant.table.VanConstants.VAN_STATUS;
import static ua.training.util.constant.table.VanConstants.VAN_TABLE;

public class VanProxy extends Van {
    @Override
    public VanStatus getVanStatus() {
        String query = new QueryBuilder()
                .select(VAN_STATUS)
                .from()
                .table(VAN_TABLE)
                .where()
                .condition(VAN_TABLE, ID)
                .built();
        DataSource dataSource = DataSourceFactory.getInstance().getDataSource();
        try (Connection connection = dataSource.getConnection()) {
            return DaoFactory
                    .getDaoFactory(connection)
                    .createVanStatusDao()
                    .findOne(getIdDesiredColumnByBeverageId(query, connection));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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

    @Override
    public List<Order> getOrders() {
        DataSource dataSource = DataSourceFactory.getInstance().getDataSource();
        try (Connection connection = dataSource.getConnection()) {
            DaoFactory daoFactory = DaoFactory.getDaoFactory(connection);
            OrderDao orderDao = daoFactory.createOrderDao();
            return orderDao.findByVan(getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
