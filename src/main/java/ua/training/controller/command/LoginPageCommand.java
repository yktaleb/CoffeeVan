package ua.training.controller.command;

import ua.training.entity.User;
import ua.training.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class LoginPageCommand implements Command {
    private static final String LOGIN_PAGE = "WEB-INF/view/login.jsp";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return LOGIN_PAGE;
    }
}
