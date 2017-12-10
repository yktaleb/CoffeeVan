package ua.training.dao;

import ua.training.entity.BeverageOrder;
import ua.training.entity.Order;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface OrderDao extends CrudDao<Order, Long> {
    List<BeverageOrder> getBeverageOrdersByOrderId(Long id) throws SQLException;

    List<Order> findByStatus(Long statusId) throws SQLException;

    List<Order> findByUser(Long userId) throws SQLException;

    List<Order> findByVan(Long vanId) throws SQLException;

    int getNumberOfRows() throws SQLException;
}
