package ua.training.controller.command;

import ua.training.entity.Role;
import ua.training.entity.User;
import ua.training.exception.LoginNotFoundException;
import ua.training.service.UserService;
import ua.training.util.ExceptionMessage;
import ua.training.util.constant.general.Pages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static ua.training.util.constant.general.Global.ADMIN_ROLE;
import static ua.training.util.constant.general.Parameters.*;

public class LoginCommand implements Command {

    private UserService userService;

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter(EMAIL);
        String password = request.getParameter(PASSWORD);
        User user = null;
        try {
            user = userService.findByEmail(email);
        } catch (LoginNotFoundException e) {
            request.getSession().setAttribute(EXCEPTION,
                    ExceptionMessage.getMessage(e.getMessage()));
            return Pages.LOGIN;
        }
        if (user == null || !user.getPassword().equals(password)) {
            request.getSession().setAttribute(EXCEPTION,
                    ExceptionMessage.getMessage(ExceptionMessage.WRONG_PASSWORD_ERROR));
            return Pages.LOGIN;
        }
        request.getSession().setAttribute(X_AUTH_TOKEN, user.getId());
        for (Role role : user.getRoles()) {
            if (role.getName().equals(ADMIN_ROLE)) {
                request.getSession().setAttribute(ADMIN, true);
            }
        }
        return Pages.INDEX;
    }
}
