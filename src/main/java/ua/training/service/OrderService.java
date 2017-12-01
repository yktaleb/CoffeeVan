package ua.training.service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface OrderService {
    void createOrder(HttpServletRequest request);
}
