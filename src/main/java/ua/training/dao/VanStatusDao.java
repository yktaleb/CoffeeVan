package ua.training.dao;

import ua.training.entity.VanStatus;

import java.sql.SQLException;

public interface VanStatusDao extends CrudDao<VanStatus, Long> {
    VanStatus findByName(String value) throws SQLException;
}
