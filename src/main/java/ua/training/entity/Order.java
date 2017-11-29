package ua.training.entity;

import java.util.Set;

public class Order {
    private Long id;
    private String address;
    private User user;
    private Van van;
    private OrderStatus status;
    private Set<BeverageOrder> beverageOrders;
}
