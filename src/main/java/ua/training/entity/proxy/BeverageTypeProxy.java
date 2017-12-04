package ua.training.entity.proxy;

import ua.training.entity.Beverage;
import ua.training.entity.BeverageType;

import java.util.List;

public class BeverageTypeProxy extends BeverageType {
    @Override
    public List<Beverage> getBeverages() {
        return super.getBeverages();
    }
}
