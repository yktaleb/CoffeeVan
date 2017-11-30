package ua.training.controller.command;

import org.apache.log4j.Logger;
import ua.training.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class CommandCreator {
    public static final String INDEX_COMMAND = "index";
    public static final String LOGIN_COMMAND = "login";
    public static final String COMMAND = "command";

    private Map<String, Command> commandMap = new HashMap<>();
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();

    private CommandCreator() {
        commandMap.put(INDEX_COMMAND, new DefaultCommand(serviceFactory.createBeverageService()));
        commandMap.put(LOGIN_COMMAND, new LoginCommand(serviceFactory.createUserService()));
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
        if (authToken == null) {
            command = commandMap.get(LOGIN_COMMAND);
        } else if (command == null) {
            command = commandMap.get(INDEX_COMMAND);
        }
        return command.execute(request, response);
    }
}
