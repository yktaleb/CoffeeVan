package ua.training.dao;

import ua.training.entity.BeverageOrder;
import ua.training.entity.Order;

import java.util.List;

public interface OrderDao extends CrudDao<Order, Long> {
    List<BeverageOrder> getBeverageOrdersByOrderId(Long id);

    List<Order> findByStatus(Long statusId);

    List<Order> findByUser(Long userId);

    List<Order> findByVan(Long vanId);
}
