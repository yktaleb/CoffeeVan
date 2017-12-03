package ua.training.service;

import ua.training.entity.Order;
import ua.training.entity.Van;

import java.util.List;
import java.util.Set;

public interface AdminService {
    List<Order> getAllOrders();
    Set<Van> getFreeVans();
    Set<Van> getBusyVans();
    void setOrderVan(Long orderId, Long vanId);
    void makeVanFree(Long vanId);
}
