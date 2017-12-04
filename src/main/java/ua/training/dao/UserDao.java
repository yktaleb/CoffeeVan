package ua.training.dao;

import ua.training.entity.User;

public interface UserDao extends CrudDao<User, Long> {
    User findByEmail(String email);

    void setUserRole(Long userId, Long roleId);
}
