package ua.training.controller;

import ua.training.entity.Beverage;

public class FieldBasketFront {
    private Beverage beverage;
    private int amount;
    private double price;

    public Beverage getBeverage() {
        return beverage;
    }

    public void setBeverage(Beverage beverage) {
        this.beverage = beverage;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public static final class FieldBasketFrontBuilder {
        private Beverage beverage;
        private int amount;
        private double price;

        public FieldBasketFrontBuilder setBeverage(Beverage beverage) {
            this.beverage = beverage;
            return this;
        }

        public FieldBasketFrontBuilder setAmount(int amount) {
            this.amount = amount;
            return this;
        }

        public FieldBasketFrontBuilder setPrice(double price) {
            this.price = price;
            return this;
        }

        public FieldBasketFront build() {
            FieldBasketFront fieldBasketFront = new FieldBasketFront();
            fieldBasketFront.setBeverage(beverage);
            fieldBasketFront.setAmount(amount);
            fieldBasketFront.setPrice(price);
            return fieldBasketFront;
        }
    }
}
