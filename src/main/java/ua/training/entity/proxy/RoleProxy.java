package ua.training.entity.proxy;

import ua.training.entity.Role;
import ua.training.entity.User;

import java.util.List;

public class RoleProxy extends Role {
    @Override
    public List<User> getUsers() {
        return super.getUsers();
    }
}
