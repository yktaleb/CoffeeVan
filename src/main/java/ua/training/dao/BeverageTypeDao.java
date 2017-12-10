package ua.training.dao;

import ua.training.entity.BeverageType;

import java.sql.SQLException;
import java.util.Optional;

public interface BeverageTypeDao extends CrudDao<BeverageType, Long> {
    BeverageType findByName(String value) throws SQLException;
}
