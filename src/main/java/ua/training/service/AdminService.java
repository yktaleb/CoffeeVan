package ua.training.service;

import ua.training.entity.Order;
import ua.training.entity.Van;
import ua.training.exception.VanCapacityException;

import java.util.List;

public interface AdminService {
    List<Order> getAllOrders();

    List<Van> getFreeVans();

    List<Van> getBusyVans();

    void setOrderVan(Long orderId, Long vanId) throws VanCapacityException;

    void makeVanFree(Long vanId);
}
