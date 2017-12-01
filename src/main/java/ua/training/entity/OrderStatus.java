package ua.training.entity;

import java.util.Set;

public class OrderStatus implements Entity<Long> {
    private Long id;
    private String name;
    private Set<Order> orders;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }


    public static final class OrderStatusBuilder {
        private Long id;
        private String name;
        private Set<Order> orders;

        public OrderStatusBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public OrderStatusBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public OrderStatusBuilder setOrders(Set<Order> orders) {
            this.orders = orders;
            return this;
        }

        public OrderStatus build() {
            OrderStatus orderStatus = new OrderStatus();
            orderStatus.setId(id);
            orderStatus.setName(name);
            orderStatus.setOrders(orders);
            return orderStatus;
        }
    }
}
