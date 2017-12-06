package ua.training.controller.command;

import ua.training.entity.Role;
import ua.training.service.ServiceFactory;
import ua.training.util.constant.general.Pages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ua.training.util.constant.general.Commands.*;
import static ua.training.util.constant.general.Parameters.*;

public class CommandCreator {

    private static final String ADMIN_ROLE = "ADMIN_ROLE";

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
        commandMap.put(LANGUAGE_COMMAND, new LanguageCommand());
    }

    private static class CommandFactoryHolder {
        private static final CommandCreator instance = new CommandCreator();
    }

    public static CommandCreator getInstance() {
        return CommandFactoryHolder.instance;
    }

    public String action(HttpServletRequest request, HttpServletResponse response) throws RuntimeException {
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
                return Pages.ERROR;
            }
            commandName = commandName.split("/")[1];
        }
        Command command = commandMap.get(commandName);
        if (authToken == null
                && !LOGIN_COMMAND.equals(commandName)
                && !REGISTRATION_PAGE_COMMAND.equals(commandName)
                && !REGISTRATION_COMMAND.equals(commandName)
                && !LANGUAGE_COMMAND.equals(commandName)) {
            command = commandMap.get(LOGIN_PAGE_COMMAND);
        } else if (authToken != null && commandName == null) {
            command = commandMap.get(INDEX_COMMAND);
        }
        return command.execute(request, response);
    }
}
