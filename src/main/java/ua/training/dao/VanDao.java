package ua.training.dao;

import ua.training.entity.Van;

import java.sql.SQLException;
import java.util.List;

public interface VanDao extends CrudDao<Van, Long> {
    List<Van> findByStatus(Long statusId) throws SQLException;
}
