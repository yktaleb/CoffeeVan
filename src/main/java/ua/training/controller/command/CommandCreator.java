package ua.training.controller.command;

import ua.training.dao.BeverageDao;
import ua.training.dao.VanDao;
import ua.training.dao.factory.DaoFactory;
import ua.training.dao.factory.DataSourceFactory;
import ua.training.dao.factory.MySqlDaoFactory;
import ua.training.entity.*;
import ua.training.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

public class CommandCreator {
    public static final String INDEX_COMMAND = "index";
    public static final String LOGIN_PAGE_COMMAND = "loginPage";
    public static final String REGISTRATION_PAGE_COMMAND = "registrationPage";
    public static final String LOGIN_COMMAND = "login";
    public static final String REGISTRATION_COMMAND = "registration";
    public static final String COMMAND = "command";
    public static final String ADD_TO_BASKET_COMMAND = "addToBasket";
    public static final String SHOW_BASKET_COMMAND = "showBasket";
    public static final String CREATE_ORDER_COMMAND = "createOrder";
    public static final String ADMIN_PAGE_COMMAND = "adminPage";
    public static final String ADMIN = "admin";
    public static final String SET_ORDER_VAN_COMMAND = "setOrderVan";
    public static final String MAKE_VAN_FREE_COMMAND = "makeVanFree";
    public static final String ADMIN_ROLE = "ADMIN_ROLE";
    public static final String LOGOUT_COMMAND = "logout";
    private static final String ERROR = "WEB-INF/view/error.jsp";
    private static final String X_AUTH_TOKEN = "X-Auth-Token";

    private Map<String, Command> commandMap = new HashMap<>();
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();

    private CommandCreator() {
        commandMap.put(INDEX_COMMAND, new DefaultCommand(serviceFactory.createBeverageService()));
        commandMap.put(LOGIN_COMMAND, new LoginCommand(serviceFactory.createUserService()));
        commandMap.put(LOGIN_PAGE_COMMAND, new LoginPageCommand());
        commandMap.put(REGISTRATION_COMMAND, new RegistrationCommand(serviceFactory.createUserService()));
        commandMap.put(REGISTRATION_PAGE_COMMAND, new RegistrationPageCommand());
        commandMap.put(ADD_TO_BASKET_COMMAND, new AddToBasketCommand());
        commandMap.put(SHOW_BASKET_COMMAND, new ShowBasketCommand(serviceFactory.createBeverageService()));
        commandMap.put(CREATE_ORDER_COMMAND, new CreateOrderCommand(serviceFactory.createOrderService()));
        commandMap.put(ADMIN_PAGE_COMMAND, new AdminCommand(serviceFactory.createAdminService()));
        commandMap.put(SET_ORDER_VAN_COMMAND, new SetOrderVanCommand(serviceFactory.createAdminService()));
        commandMap.put(MAKE_VAN_FREE_COMMAND, new MakeVanFreeCommand(serviceFactory.createAdminService()));
        commandMap.put(LOGOUT_COMMAND, new LogoutCommand());
    }

    private static class CommandFactoryHolder {
        private static final CommandCreator instance = new CommandCreator();
    }

    public static CommandCreator getInstance() {
        return CommandFactoryHolder.instance;
    }

    public String action(HttpServletRequest request, HttpServletResponse response) throws RuntimeException {

        DataSource dataSource = DataSourceFactory.getInstance().getDataSource();
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            DaoFactory daoFactory = DaoFactory.getDaoFactory(connection);
            BeverageDao beverageDao = daoFactory.createBeverageDao();
            Beverage one = beverageDao.findOne(1L);
            BeverageType type = one.getType();
            BeverageQuality quality = one.getQuality();
            List<BeverageOrder> beverageOrders = one.getBeverageOrders();
            BeverageState state = one.getState();
        } catch (SQLException e) {
            e.printStackTrace();
        }



        Long authToken = (Long) request.getSession().getAttribute(X_AUTH_TOKEN);
        String commandName = request.getParameter(COMMAND);
        if (commandName != null
                && ADMIN.equals(commandName.split("/")[0])) {
            List<Role> roles = serviceFactory.createUserService().getCurrentUser(request).getRoles();
            boolean isAdmin = false;
            for (Role role : roles) {
                if (ADMIN_ROLE.equals(role.getName())) {
                    isAdmin = true;
                    break;
                }
            }
            if (!isAdmin) {
                return ERROR;
            }
            commandName = commandName.split("/")[1];
        }
        Command command = commandMap.get(commandName);
        if (authToken == null
                && !LOGIN_COMMAND.equals(commandName)
                && !REGISTRATION_PAGE_COMMAND.equals(commandName)
                && !REGISTRATION_COMMAND.equals(commandName)) {
            command = commandMap.get(LOGIN_PAGE_COMMAND);
        } else if (authToken != null && commandName == null) {
            command = commandMap.get(INDEX_COMMAND);
        }
        return command.execute(request, response);
    }
}
