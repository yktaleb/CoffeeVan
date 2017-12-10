package ua.training.service.impl;

import ua.training.dao.RoleDao;
import ua.training.dao.UserDao;
import ua.training.dao.factory.DaoFactory;
import ua.training.dao.factory.DataSourceFactory;
import ua.training.entity.Role;
import ua.training.entity.User;
import ua.training.exception.IncorrectUserDataException;
import ua.training.exception.LoginAlreadyExistsException;
import ua.training.exception.LoginNotFoundException;
import ua.training.service.UserService;
import ua.training.util.ConnectionUtil;
import ua.training.util.ExceptionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import static ua.training.util.ExceptionMessage.EMAIL_PATTERN_ERROR;
import static ua.training.util.ExceptionMessage.PASSWORD_PATTERN_ERROR;
import static ua.training.util.ExceptionMessage.PHONE_NUMBER_PATTERN_ERROR;
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
    public User findByEmail(String email) throws LoginNotFoundException {
        User user = null;
        DataSource dataSource = DataSourceFactory.getInstance().getDataSource();
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            DaoFactory daoFactory = DaoFactory.getDaoFactory(connection);
            UserDao userDao = daoFactory.createUserDao();
            user = userDao.findByEmail(email);
            if (user == null) {
                throw new LoginNotFoundException(email, ExceptionMessage.EMAIL_NOT_FOUND_ERROR);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User register(User user) throws LoginAlreadyExistsException, IncorrectUserDataException {
        validation(user);
        User savedUser = null;
        DataSource dataSource = DataSourceFactory.getInstance().getDataSource();
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            DaoFactory daoFactory = DaoFactory.getDaoFactory(connection);
            UserDao userDao = daoFactory.createUserDao();
            RoleDao roleDao = daoFactory.createRoleDao();
            Role role = roleDao.findByName(USER_ROLE);
            savedUser = userDao.save(user);
            userDao.setUserRole(savedUser.getId(), role.getId());
            connection.commit();
        } catch (SQLIntegrityConstraintViolationException e) {
            ConnectionUtil.rollback(connection);
            throw new LoginAlreadyExistsException(ExceptionMessage.EMAIL_EXIST_ERROR);
        } catch (SQLException e) {
            ConnectionUtil.rollback(connection);
            e.printStackTrace();
        } finally {
            ConnectionUtil.close(connection);
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

    public void validation(User user) throws IncorrectUserDataException {
        if (!isPhoneNumberValid(user.getPhoneNumber())) {
            throw new IncorrectUserDataException(PHONE_NUMBER_PATTERN_ERROR);
        }
        if (!isEmailValid(user.getEmail())) {
            throw new IncorrectUserDataException(EMAIL_PATTERN_ERROR);
        }
        if (!isPasswordValid(user.getPassword())) {
            throw new IncorrectUserDataException(PASSWORD_PATTERN_ERROR);
        }
    }

    boolean isEmailValid(String email) {
        final String regex = "^[a-zA-Z0-9.!#$%&'*+\\/=?^_`\\{|\\}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(regex);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    boolean isPasswordValid(String password) {
        if (password.length() < 8 || password.length() > 20)
            return false;
        final String regex = "^[a-zA-Z0-9!@#$%^&*()_+|~\\-=\\/‘\\{\\}\\[\\]:\";’<>?,./]+$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(regex);
        java.util.regex.Matcher m = p.matcher(password);
        return m.matches();
    }

    boolean isPhoneNumberValid(String phone) {
        final String regex = "^\\+38\\(0[\\d]{2}\\)[\\d]{7}$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(regex);
        java.util.regex.Matcher m = p.matcher(phone);
        return m.matches();
    }
}
