package ua.training.service;

import ua.training.entity.Order;
import ua.training.entity.proxy.OrderProxy;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface OrderService {
    void createOrder(HttpServletRequest request);
    Order save(Order order);
    List<Order> getAll();
}
