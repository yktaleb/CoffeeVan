package ua.training.entity.proxy;

import ua.training.entity.Order;
import ua.training.entity.Role;
import ua.training.entity.User;

import java.util.List;

public class UserProxy extends User {
    @Override
    public List<Role> getRoles() {
        return super.getRoles();
    }

    @Override
    public List<Order> getOrders() {
        return super.getOrders();
    }
}
