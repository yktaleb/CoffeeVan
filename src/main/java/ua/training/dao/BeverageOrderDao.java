package ua.training.dao;

import ua.training.entity.BeverageOrder;

import java.util.List;

public interface BeverageOrderDao extends CrudDao<BeverageOrder, Long> {
    List<BeverageOrder> findByOrder(Long orderId);
}
