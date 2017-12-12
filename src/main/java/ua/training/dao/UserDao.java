package ua.training.dao;

import ua.training.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao extends CrudDao<User, Long> {
    User findByEmail(String email) throws SQLException;

    void setUserRole(Long userId, Long roleId);

    List<User> findByRole(Long id) throws SQLException;
}
