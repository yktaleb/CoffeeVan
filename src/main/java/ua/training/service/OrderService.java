package ua.training.service;

import ua.training.entity.Order;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface OrderService {
    void createOrder(HttpServletRequest request);
    Order save(Order order);
}
