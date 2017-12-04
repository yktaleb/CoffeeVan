package ua.training.entity.proxy;

import ua.training.entity.Order;
import ua.training.entity.OrderStatus;

import java.util.List;

public class OrderStatusProxy extends OrderStatus {
    @Override
    public List<Order> getOrders() {
        return super.getOrders();
    }
}
