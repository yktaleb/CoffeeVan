package ua.training.controller;

import ua.training.entity.BeverageOrder;
import ua.training.entity.Order;

import java.util.List;

public class FrontOrder {
    private Order order;
    private List<BeverageOrder> beverageOrders;
    private double totalWeight;
    private double totalVolume;
    private double totalPrice;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<BeverageOrder> getBeverageOrders() {
        return beverageOrders;
    }

    public void setBeverageOrders(List<BeverageOrder> beverageOrders) {
        this.beverageOrders = beverageOrders;
    }

    public double getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(double totalWeight) {
        this.totalWeight = totalWeight;
    }

    public double getTotalVolume() {
        return totalVolume;
    }

    public void setTotalVolume(double totalVolume) {
        this.totalVolume = totalVolume;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public static final class FrontOrderBuilder {
        private Order order;
        private List<BeverageOrder> beverageOrder;
        private double totalWeight;
        private double totalVolume;
        private double totalPrice;

        public FrontOrderBuilder setOrder(Order order) {
            this.order = order;
            return this;
        }

        public FrontOrderBuilder setBeverageOrder(List<BeverageOrder> beverageOrder) {
            this.beverageOrder = beverageOrder;
            return this;
        }

        public FrontOrderBuilder setTotalWeight(double totalWeight) {
            this.totalWeight = totalWeight;
            return this;
        }

        public FrontOrderBuilder setTotalVolume(double totalVolume) {
            this.totalVolume = totalVolume;
            return this;
        }

        public FrontOrderBuilder setTotalPrice(double totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        public FrontOrder build() {
            FrontOrder frontOrder = new FrontOrder();
            frontOrder.setOrder(order);
            frontOrder.setBeverageOrders(beverageOrder);
            frontOrder.setTotalVolume(totalVolume);
            frontOrder.setTotalWeight(totalWeight);
            frontOrder.setTotalPrice(totalPrice);
            return frontOrder;
        }
    }
}
