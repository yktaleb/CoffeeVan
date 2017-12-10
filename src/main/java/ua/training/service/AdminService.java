package ua.training.service;

import ua.training.entity.Order;
import ua.training.entity.Van;
import ua.training.exception.VanCapacityException;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface AdminService {
    List<Order> getAllOrders();

    List<Van> getFreeVans();

    List<Van> getBusyVans();

    void setOrderVan(Long orderId, Long vanId) throws VanCapacityException;

    void makeVanFree(Long vanId);

    Optional<Long> getNumberOfOrders();
}
