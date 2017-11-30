package ua.training.dao;

import ua.training.entity.BeverageQuality;
import ua.training.entity.BeverageState;

import java.util.Optional;

public interface BeverageQualityDao extends CrudDao<BeverageQuality, Long> {
    Optional<BeverageQuality> findByName(String value);

}
