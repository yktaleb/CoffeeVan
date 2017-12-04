package ua.training.dao;

import ua.training.entity.User;

import java.util.List;

public interface UserDao extends CrudDao<User, Long> {
    User findByEmail(String email);

    void setUserRole(Long userId, Long roleId);

    List<User> findByRole(Long id);
}
