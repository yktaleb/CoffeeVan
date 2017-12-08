package ua.training.service;

import ua.training.entity.User;
import ua.training.exception.IncorrectUserDataException;
import ua.training.exception.LoginAlreadyExistsException;
import ua.training.exception.LoginNotFoundException;

import javax.servlet.http.HttpServletRequest;

public interface UserService {
    User findByEmail(String email) throws LoginNotFoundException;

    User register(User user) throws LoginAlreadyExistsException, IncorrectUserDataException;

    User getCurrentUser(HttpServletRequest request);
}
