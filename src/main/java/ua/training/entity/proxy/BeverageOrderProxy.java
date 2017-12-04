package ua.training.entity.proxy;

import ua.training.entity.Beverage;
import ua.training.entity.BeverageOrder;
import ua.training.entity.Order;

public class BeverageOrderProxy extends BeverageOrder {
    @Override
    public Beverage getBeverage() {
        return super.getBeverage();
    }

    @Override
    public Order getOrder() {
        return super.getOrder();
    }
}
