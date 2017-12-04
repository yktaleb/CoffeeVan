package ua.training.dao;

import ua.training.entity.BeverageState;

import java.util.Optional;

public interface BeverageStateDao extends CrudDao<BeverageState, Long> {
    BeverageState findByName(String value);
}
