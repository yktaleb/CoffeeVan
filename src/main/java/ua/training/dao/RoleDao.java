package ua.training.dao;

import ua.training.entity.Role;

import java.util.Optional;
import java.util.Set;

public interface RoleDao extends CrudDao<Role, Long> {
    Optional<Role> findByName(String value);
    Set<Role> findByUser(Long userId);
}
