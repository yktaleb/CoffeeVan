package ua.training.entity;

import java.util.Set;

public class Beverage {
    private Long id;
    private String name;
    private Double price;
    private Double weight;
    private Double volume;
    private BeverageType type;
    private BeverageState state;
    private BeverageQuality quality;
    private Set<BeverageOrder> beverageOrders;
}
