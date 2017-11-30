package ua.training.dao;

import ua.training.entity.BeverageState;
import ua.training.entity.BeverageType;

import java.util.Optional;

public interface BeverageStateDao extends CrudDao<BeverageState, Long> {
    Optional<BeverageState> findByName(String value);
}
