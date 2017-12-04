package ua.training.entity.proxy;

import ua.training.entity.Beverage;
import ua.training.entity.BeverageState;

import java.util.List;

public class BeverageStateProxy extends BeverageState {
    @Override
    public List<Beverage> getBeverages() {
        return super.getBeverages();
    }
}
