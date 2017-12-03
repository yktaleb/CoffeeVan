package ua.training.dao;

import ua.training.entity.BeverageOrder;
import ua.training.entity.Order;

import java.util.List;

public interface OrderDao extends CrudDao<Order, Long> {
    List<BeverageOrder> getBeverageOrdersByOrderId(Long id);
}
