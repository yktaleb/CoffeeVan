package ua.training;

import org.junit.Ignore;
import org.junit.Test;
import ua.training.dao.UserDao;
import ua.training.dao.factory.DaoFactory;
import ua.training.dao.factory.DataSourceFactory;
import ua.training.entity.User;
import ua.training.service.UserService;
import ua.training.service.impl.UserServiceImpl;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceTest {
    private UserService userService;
    private UserDao userDao;
    private DataSource dataSource;
    private Connection connection;
    private DaoFactory daoFactory;
    private DataSourceFactory dataSourceFactory;
    private User user;

    //TODO remake services for mocking dao for services;
    private void doInitialization() {
        userDao = mock(UserDao.class);
        dataSource = mock(DataSource.class);
        connection = mock(Connection.class);
        daoFactory = mock(DaoFactory.class);
        dataSourceFactory = mock(DataSourceFactory.class);
        user = mock(User.class);
        userService = UserServiceImpl.getInstance();
    }

    private void stubDaoFactory() throws SQLException{
        when(dataSourceFactory.getDataSource()).thenReturn(dataSource);
        when(dataSource.getConnection()).thenReturn(connection);
        when(daoFactory.createUserDao()).thenReturn(userDao);
    }


    @Test
    @Ignore
    public void testFindUser() throws SQLException {
        doInitialization();
        stubDaoFactory();

        user = userService.findByEmail("a").get();
        assertNotNull(user);
        assertEquals(user.getFirstName(), "a");
        assertNotEquals(user.getPassword(), "a");


    }
}
