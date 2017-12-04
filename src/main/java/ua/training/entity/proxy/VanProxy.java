package ua.training.entity.proxy;

import ua.training.entity.Order;
import ua.training.entity.Van;
import ua.training.entity.VanStatus;

import java.util.List;

public class VanProxy extends Van {
    @Override
    public VanStatus getVanStatus() {
        return super.getVanStatus();
    }

    @Override
    public List<Order> getOrders() {
        return super.getOrders();
    }
}
