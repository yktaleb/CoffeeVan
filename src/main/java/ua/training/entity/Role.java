package ua.training.entity;

import ua.training.entity.proxy.RoleProxy;

import java.util.List;
import java.util.Set;

public class Role implements Entity<Long> {
    private Long id;
    private String name;
    private List<User> users;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }


    public static final class RoleBuilder {
        private Long id;
        private String name;
        private List<User> users;

        public RoleBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public RoleBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public RoleBuilder setUsers(List<User> users) {
            this.users = users;
            return this;
        }

        public Role buildRole() {
            Role role = new Role();
            role.setId(id);
            role.setName(name);
            role.setUsers(users);
            return role;
        }

        public Role buildRoleProxy() {
            RoleProxy role = new RoleProxy();
            role.setId(id);
            role.setName(name);
            role.setUsers(users);
            return role;
        }
    }
}
