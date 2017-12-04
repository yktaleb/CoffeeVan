package ua.training.service;

import ua.training.entity.Beverage;

import java.util.List;
import java.util.Optional;

public interface BeverageService {
    List<Beverage> findAllBeverage();
    Optional<Beverage> findById(Long id);
    List<Beverage> getSortedByPrice();
    List<Beverage> getSortedByQuality();
}
