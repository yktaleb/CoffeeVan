package ua.training.entity;

public class BeverageOrder {
    private Long id;
    private Integer amount;
    private Beverage beverage;
    private Order order;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Beverage getBeverage() {
        return beverage;
    }

    public void setBeverage(Beverage beverage) {
        this.beverage = beverage;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }


    public static final class BeverageOrderBuilder {
        private Long id;
        private Integer amount;
        private Beverage beverage;
        private Order order;

        public BeverageOrderBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public BeverageOrderBuilder setAmount(Integer amount) {
            this.amount = amount;
            return this;
        }

        public BeverageOrderBuilder setBeverage(Beverage beverage) {
            this.beverage = beverage;
            return this;
        }

        public BeverageOrderBuilder setOrder(Order order) {
            this.order = order;
            return this;
        }

        public BeverageOrder build() {
            BeverageOrder beverageOrder = new BeverageOrder();
            beverageOrder.setId(id);
            beverageOrder.setAmount(amount);
            beverageOrder.setBeverage(beverage);
            beverageOrder.setOrder(order);
            return beverageOrder;
        }
    }
}
