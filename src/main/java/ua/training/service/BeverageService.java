package ua.training.service;

import ua.training.entity.Beverage;

import java.util.List;

public interface BeverageService {
    List<Beverage> findAllBeverage();

    Beverage findById(Long id);

    List<Beverage> getSortedByPrice();

    List<Beverage> getSortedByQuality();
}
