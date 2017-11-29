package ua.training.controller;

import ua.training.dao.UserDao;
import ua.training.dao.datasource.ConnectionPool;
import ua.training.dao.factory.DaoFactory;
import ua.training.dao.factory.DataSourceFactory;
import ua.training.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@WebServlet("/hello")
public class MainController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        DataSource dataSource = ConnectionPool.getDataSource();
//        Connection conn = null;
//        try {
//            conn = dataSource.getConnection();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        StringBuilder msg = new StringBuilder();
//        try (Statement stm = conn.createStatement()) {
//            String query = "show tables;";
//            ResultSet rs = stm.executeQuery(query);
//            int i = 0;
//            while (rs.next()) {
//                i++;
//            }
//        } catch (SQLException e) {
//            System.err.println(e.getMessage());
//        } finally {
//            if (conn != null) {
//                try {
//                    conn.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//            conn = null; // prevent any future access
//        }
        DataSource dataSource = DataSourceFactory.getInstance().getDataSource();
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            DaoFactory daoFactory = DaoFactory.getDaoFactory(connection);
            UserDao userDao = daoFactory.createUserDao();
//            User user = new User.Builder()
//                    .setId(1L)
//                    .setEmail("talebqq@gai.com")
//                    .setPassword("1348")
//                    .setFirstName("Yarik")
//                    .setLastName("Taleb")
//                    .setPhoneNumber("1921212")
//                    .build();
            userDao.delete(2L);
            connection.commit();
        } catch (SQLException e) {
//            throw new DaoException(e.getMessage());
            e.printStackTrace();
        }
    }
}
