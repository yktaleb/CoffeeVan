package ua.training.dao;

import ua.training.entity.BeverageQuality;

import java.sql.SQLException;

public interface BeverageQualityDao extends CrudDao<BeverageQuality, Long> {
    BeverageQuality findByName(String value) throws SQLException;
}
