package ua.training.service;

import ua.training.entity.Order;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface OrderService {
    void createOrder(HttpServletRequest request);
    Order save(Order order);
    List<Order> getAll();
}
