package ua.training.dao;

import ua.training.entity.Van;

import java.util.Set;

public interface VanDao extends CrudDao<Van, Long> {
    Set<Van> findAllByStatus(String status);
}
