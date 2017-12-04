package ua.training.service;

import ua.training.entity.User;
import ua.training.exception.LoginAlreadyExistsException;

import javax.servlet.http.HttpServletRequest;

public interface UserService {
    User findByEmail(String email);

    User register(User user) throws LoginAlreadyExistsException;

    User getCurrentUser(HttpServletRequest request);
}
