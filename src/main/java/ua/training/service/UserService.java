package ua.training.service;

import ua.training.entity.User;
import ua.training.exception.LoginAlreadyExistsException;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public interface UserService {
    Optional<User> findByEmail(String email);
    User register(User user) throws LoginAlreadyExistsException;
    Optional<User> getCurrentUser(HttpServletRequest request);
}
