package ua.training.entity.proxy;

import ua.training.dao.UserDao;
import ua.training.dao.factory.DaoFactory;
import ua.training.dao.factory.DataSourceFactory;
import ua.training.entity.Role;
import ua.training.entity.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class RoleProxy extends Role {
    @Override
    public List<User> getUsers() {
        if (super.getUsers() == null) {
            DataSource dataSource = DataSourceFactory.getInstance().getDataSource();
            try (Connection connection = dataSource.getConnection()) {
                DaoFactory daoFactory = DaoFactory.getDaoFactory(connection);
                UserDao userDao = daoFactory.createUserDao();
                return userDao.findByRole(getId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return super.getUsers();
        }
        return super.getUsers();
    }
}
