package ua.training.controller;

import ua.training.dao.UserDao;
import ua.training.dao.datasource.ConnectionPool;
import ua.training.dao.factory.DaoFactory;
import ua.training.dao.factory.DataSourceFactory;
import ua.training.entity.*;

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
        DataSource dataSource = DataSourceFactory.getInstance().getDataSource();
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            DaoFactory daoFactory = DaoFactory.getDaoFactory(connection);
//            UserDao userDao = daoFactory.createUserDao();
//            User user = new User.UserBuilder()
//                    .setId(1L)
//                    .setEmail("talebqq@gai.com")
//                    .setPassword("1348")
//                    .setFirstName("Yarik")
//                    .setLastName("Taleb")
//                    .setPhoneNumber("1921212")
//                    .build();
//            userDao.delete(2L);
//            daoFactory.createBeverageTypeDao().save(new BeverageType.BeverageTypeBuilder().setName("coffee").build());
            Optional<BeverageType> coffee = daoFactory.createBeverageTypeDao().findByName("coffee");
            BeverageType beverageType = coffee.get();
//            daoFactory.createBeverageStateDao().save(new BeverageState.BeverageStateBuilder().setName("grains").build());
            BeverageState grains = daoFactory.createBeverageStateDao().findByName("grains").get();
            grains.getId();
//            daoFactory.createBeverageQualityDao().save(new BeverageQuality.BeverageQualityBuilder().setName("VIP").build());

            connection.commit();
        } catch (SQLException e) {
//            throw new DaoException(e.getMessage());
            e.printStackTrace();
        }
    }
}
