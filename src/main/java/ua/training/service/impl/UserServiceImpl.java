package ua.training.service.impl;

import ua.training.dao.RoleDao;
import ua.training.dao.UserDao;
import ua.training.dao.factory.DaoFactory;
import ua.training.dao.factory.DataSourceFactory;
import ua.training.entity.Role;
import ua.training.entity.User;
import ua.training.exception.LoginAlreadyExistsException;
import ua.training.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static ua.training.util.constant.general.Global.USER_ROLE;
import static ua.training.util.constant.general.Parameters.X_AUTH_TOKEN;

public class UserServiceImpl implements UserService {

    private UserServiceImpl() {
    }

    private static final class UserServiceImplHolder {
        private static final UserServiceImpl instance = new UserServiceImpl();
    }

    public static UserServiceImpl getInstance() {
        return UserServiceImplHolder.instance;
    }

    @Override
    public User findByEmail(String email) {
        User user = null;
        DataSource dataSource = DataSourceFactory.getInstance().getDataSource();
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            DaoFactory daoFactory = DaoFactory.getDaoFactory(connection);
            UserDao userDao = daoFactory.createUserDao();
            user = userDao.findByEmail(email);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User register(User user) throws LoginAlreadyExistsException {
        User savedUser = null;
        DataSource dataSource = DataSourceFactory.getInstance().getDataSource();
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            DaoFactory daoFactory = DaoFactory.getDaoFactory(connection);
            UserDao userDao = daoFactory.createUserDao();
            RoleDao roleDao = daoFactory.createRoleDao();
            Role role = roleDao.findByName(USER_ROLE);
            savedUser = userDao.save(user);
            userDao.setUserRole(savedUser.getId(), role.getId());
            connection.commit();
        }  catch (SQLIntegrityConstraintViolationException e) {
            throw new LoginAlreadyExistsException(e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return savedUser;
    }

    @Override
    public User getCurrentUser(HttpServletRequest request) {
        Long id = (Long) request.getSession().getAttribute(X_AUTH_TOKEN);
        User user = null;
        DataSource dataSource = DataSourceFactory.getInstance().getDataSource();
        try (Connection connection = dataSource.getConnection()) {
            DaoFactory daoFactory = DaoFactory.getDaoFactory(connection);
            UserDao userDao = daoFactory.createUserDao();
            user = userDao.findOne(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

}
