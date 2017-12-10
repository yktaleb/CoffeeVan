package ua.training.dao;

import ua.training.entity.Beverage;

import java.sql.SQLException;
import java.util.List;

public interface BeverageDao extends CrudDao<Beverage, Long> {
    List<Beverage> getSortedByPrice() throws SQLException;

    List<Beverage> getSortedByQuality() throws SQLException;

    List<Beverage> findByQuality(Long qualityId) throws SQLException;

    List<Beverage> findByState(Long stateId) throws SQLException;

    List<Beverage> findByType(Long typeId) throws SQLException;
}
