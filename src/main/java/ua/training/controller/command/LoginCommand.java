package ua.training.controller.command;

import ua.training.entity.Beverage;
import ua.training.entity.User;
import ua.training.service.BeverageService;
import ua.training.service.UserService;
import ua.training.util.constant.general.Pages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

public class LoginCommand implements Command {
    private static final String X_AUTH_TOKEN = "X-Auth-Token";

    private UserService userService;

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String email = (String) request.getParameter("email");
        String password = (String) request.getParameter("password");
        User user = userService.findByEmail(email);
        if (user == null || !user.getPassword().equals(password)) {
            return Pages.LOGIN;
        }
        request.getSession().setAttribute(X_AUTH_TOKEN, user.getId());
        return Pages.INDEX;
    }
}
