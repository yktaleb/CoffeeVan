package ua.training.entity.proxy;

import ua.training.entity.Order;
import ua.training.entity.Van;
import ua.training.entity.VanStatus;

import java.util.List;

public class VanStatusProxy extends VanStatus {
    @Override
    public List<Van> getVans() {
        return super.getVans();
    }
}
