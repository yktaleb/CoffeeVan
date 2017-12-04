package ua.training.controller.command;

import ua.training.entity.User;
import ua.training.exception.LoginAlreadyExistsException;
import ua.training.service.UserService;
import ua.training.util.constant.general.Pages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationCommand implements Command {

    private UserService userService;

    public RegistrationCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String email = (String) request.getParameter("email");
        String password = (String) request.getParameter("password");
        String firstName = (String) request.getParameter("firstName");
        String lastName = (String) request.getParameter("lastName");
        String phoneNumber = (String) request.getParameter("phoneNumber");
        try {
            User user = userService.register(
                    new User.UserBuilder()
                            .setEmail(email)
                            .setPassword(password)
                            .setFirstName(firstName)
                            .setLastName(lastName)
                            .setPhoneNumber(phoneNumber)
                            .buildUser()
            );
        } catch (LoginAlreadyExistsException e) {
            return Pages.REGISTRATION;
        }
        return Pages.LOGIN;
    }
}
