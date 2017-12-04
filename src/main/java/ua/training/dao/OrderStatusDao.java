package ua.training.dao;

import ua.training.entity.OrderStatus;

import java.util.Optional;

public interface OrderStatusDao extends CrudDao<OrderStatus, Long> {
    OrderStatus findByName(String value);
}
