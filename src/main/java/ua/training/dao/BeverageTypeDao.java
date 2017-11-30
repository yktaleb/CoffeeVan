package ua.training.dao;

import ua.training.entity.BeverageType;

import java.util.Optional;

public interface BeverageTypeDao extends CrudDao<BeverageType, Long> {
    Optional<BeverageType> findByName(String value);
}
