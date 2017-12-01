package ua.training.controller.command;

import org.apache.log4j.Logger;
import ua.training.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class CommandCreator {
    public static final String INDEX_COMMAND = "index";
    public static final String LOGIN_PAGE_COMMAND = "loginPage";
    public static final String REGISTRATION_PAGE_COMMAND = "registrationPage";
    public static final String LOGIN_COMMAND = "login";
    public static final String REGISTRATION_COMMAND = "registration";
    public static final String COMMAND = "command";
    public static final String ADD_TO_BASKET_COMMAND = "addToBasket";
    public static final String SHOW_BASKET_COMMAND = "showBasket";

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
    }

    private static class CommandFactoryHolder {
        private static final CommandCreator instance = new CommandCreator();
    }

    public static CommandCreator getInstance() {
        return CommandFactoryHolder.instance;
    }

    public String action(HttpServletRequest request, HttpServletResponse response) throws RuntimeException {
        Long authToken = (Long) request.getSession().getAttribute("X-Auth-Token");
        String commandName = request.getParameter(COMMAND);
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
