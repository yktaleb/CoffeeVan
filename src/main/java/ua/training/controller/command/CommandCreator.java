package ua.training.controller.command;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class CommandCreator {
    public static final String INDEX_COMMAND = "index";

    private Map<String, Command> commandMap = new HashMap<>();
//    private ServiceFactory serviceFactory = ServiceFactory.getInstance();

    private CommandCreator() {
        commandMap.put(INDEX_COMMAND, new DefaultCommand());
    }

    private static class CommandFactoryHolder {
        private static final CommandCreator instance = new CommandCreator();
    }

    public static CommandCreator getInstance() {
        return CommandFactoryHolder.instance;
    }

    public String action(HttpServletRequest request, HttpServletResponse response) throws RuntimeException {
        String commandName = request.getParameter("command");
        Command command = commandMap.get(commandName);
        if (command == null) {
            command = new DefaultCommand();
        }
        return command.execute(request, response);
    }
}
