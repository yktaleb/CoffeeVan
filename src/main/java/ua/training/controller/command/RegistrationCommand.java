package ua.training.controller.command;

import ua.training.entity.User;
import ua.training.exception.IncorrectUserDataException;
import ua.training.exception.LoginAlreadyExistsException;
import ua.training.service.UserService;
import ua.training.util.ExceptionMessage;
import ua.training.util.constant.general.Pages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static ua.training.util.constant.general.Parameters.EXCEPTION;

public class RegistrationCommand implements Command {

    private UserService userService;

    public RegistrationCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String phoneNumber = request.getParameter("phoneNumber");
        try {
            userService.register(
                    new User.UserBuilder()
                            .setEmail(email)
                            .setPassword(password)
                            .setFirstName(firstName)
                            .setLastName(lastName)
                            .setPhoneNumber(phoneNumber)
                            .buildUser()
            );
        } catch (LoginAlreadyExistsException | IncorrectUserDataException e) {
            request.getSession().setAttribute(EXCEPTION, ExceptionMessage.getMessage(e.getMessage()));
            return Pages.REGISTRATION;
        }
        return Pages.LOGIN;
    }
}
