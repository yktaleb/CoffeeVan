package ua.training.dao;

import ua.training.entity.Role;

import java.util.Optional;

public interface RoleDao extends CrudDao<Role, Long> {
    Optional<Role> findByName(String value);

}
