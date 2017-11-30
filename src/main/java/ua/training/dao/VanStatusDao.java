package ua.training.dao;

import ua.training.entity.BeverageType;
import ua.training.entity.VanStatus;

import java.util.Optional;

public interface VanStatusDao extends CrudDao<VanStatus, Long> {
    Optional<VanStatus> findByName(String value);

}
