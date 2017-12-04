package ua.training.entity.proxy;

import ua.training.entity.Beverage;
import ua.training.entity.BeverageQuality;

import java.util.List;

public class BeverageQualityProxy extends BeverageQuality {
    @Override
    public List<Beverage> getBeverages() {
        return super.getBeverages();
    }
}
