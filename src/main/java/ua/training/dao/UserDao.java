package ua.training.dao;

import ua.training.entity.User;
import ua.training.exception.UniqueException;

import java.util.Optional;

public interface UserDao extends CrudDao<User, Long> {
    Optional<User> findByEmail(String email);
    void setUserRole(Long userId, Long roleId);
}
