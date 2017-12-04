package ua.training.controller.command;

import ua.training.entity.User;
import ua.training.service.UserService;
import ua.training.util.constant.general.Pages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class LogoutCommand implements Command {
    private static final String X_AUTH_TOKEN = "X-Auth-Token";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute(X_AUTH_TOKEN);
        return Pages.LOGIN;
    }
}
