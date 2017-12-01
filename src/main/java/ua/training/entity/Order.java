package ua.training.entity;

import java.util.Set;

public class Order implements Entity<Long> {
    private Long id;
    private String address;
    private User user;
    private Van van;
    private OrderStatus status;
    private Set<BeverageOrder> beverageOrders;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Van getVan() {
        return van;
    }

    public void setVan(Van van) {
        this.van = van;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Set<BeverageOrder> getBeverageOrders() {
        return beverageOrders;
    }

    public void setBeverageOrders(Set<BeverageOrder> beverageOrders) {
        this.beverageOrders = beverageOrders;
    }

    public static final class OrderBuilder {
        private Long id;
        private String address;
        private User user;
        private Van van;
        private OrderStatus status;
        private Set<BeverageOrder> beverageOrders;

        public OrderBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public OrderBuilder setAddress(String address) {
            this.address = address;
            return this;
        }

        public OrderBuilder setUser(User user) {
            this.user = user;
            return this;
        }

        public OrderBuilder setVan(Van van) {
            this.van = van;
            return this;
        }

        public OrderBuilder setStatus(OrderStatus status) {
            this.status = status;
            return this;
        }

        public OrderBuilder setBeverageOrders(Set<BeverageOrder> beverageOrders) {
            this.beverageOrders = beverageOrders;
            return this;
        }

        public Order build() {
            Order order = new Order();
            order.setId(id);
            order.setAddress(address);
            order.setUser(user);
            order.setVan(van);
            order.setStatus(status);
            order.setBeverageOrders(beverageOrders);
            return order;
        }
    }
}
