package ua.training.dao;

import ua.training.entity.Beverage;

import java.util.List;

public interface BeverageDao extends CrudDao<Beverage, Long> {
    List<Beverage> getSortedByPrice();

    List<Beverage> getSortedByQuality();

    List<Beverage> findByQuality(Long qualityId);
}
