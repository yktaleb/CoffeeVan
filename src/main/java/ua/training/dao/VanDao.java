package ua.training.dao;

import ua.training.entity.Van;

import java.util.List;

public interface VanDao extends CrudDao<Van, Long> {
    List<Van> findAllByStatus(Long statusId);
}
