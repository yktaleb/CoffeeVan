package ua.training.dao;

import ua.training.entity.VanStatus;

public interface VanStatusDao extends CrudDao<VanStatus, Long> {
    VanStatus findByName(String value);
}
