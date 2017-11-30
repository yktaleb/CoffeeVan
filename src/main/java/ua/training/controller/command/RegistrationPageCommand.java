package ua.training.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationPageCommand implements Command {
    private static final String REGISTRATION_PAGE = "WEB-INF/view/registration.jsp";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return REGISTRATION_PAGE;
    }
}
