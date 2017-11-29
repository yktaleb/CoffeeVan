package ua.training.entity;

import java.util.Set;

public class User {
    private Long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Set<Role> roles;
    private Set<Order> orders;
}
