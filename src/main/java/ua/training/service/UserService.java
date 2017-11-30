package ua.training.service;

import ua.training.entity.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findByEmail(String email);
}
