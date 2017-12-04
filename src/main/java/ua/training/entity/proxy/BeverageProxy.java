package ua.training.entity.proxy;

import ua.training.entity.*;

import java.util.List;

public class BeverageProxy extends Beverage {
    @Override
    public BeverageTypeProxy getType() {
        return super.getType();
    }

    @Override
    public BeverageState getState() {
        return super.getState();
    }

    @Override
    public BeverageQualityProxy getQuality() {
        return super.getQuality();
    }

    @Override
    public List<BeverageOrder> getBeverageOrders() {
        return super.getBeverageOrders();
    }
}
