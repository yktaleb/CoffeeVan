package ua.training;

import org.junit.Test;
import ua.training.controller.command.LoginCommand;
import ua.training.entity.Role;
import ua.training.entity.User;
import ua.training.service.UserService;
import ua.training.util.constant.general.Pages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LoginCommandTest {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession httpSession;
    private LoginCommand loginCommand;
    private UserService userService;
    private User user;

    private void doInitialization() {
        userService = mock(UserService.class);
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        httpSession = mock(HttpSession.class);
        user = mock(User.class);
        loginCommand = new LoginCommand(userService);
    }

    @Test
    public void testExecuteCommand() throws SQLException {
        doInitialization();
        String email = "user@gmail.com";
        String password = "12345678";
        Long id = 1L;
        when(user.getEmail()).thenReturn(email);
        when(user.getPassword()).thenReturn(password);
        when(user.getId()).thenReturn(id);
        when(userService.findByEmail(anyString())).thenReturn(user);
        when(user.getRoles()).thenReturn(Arrays.asList(new Role.RoleBuilder().setId(1L).setName("USER_ROLE").buildRole()));
        when(request.getParameter("email")).thenReturn(email);
        when(request.getParameter("password")).thenReturn(password);
        when(request.getSession()).thenReturn(httpSession);
//        mock(httpSession.setAttribute("X-Auth-Token", anyString())).then(httpSession.setAttribute("X-Auth-Token", user.getId()));
        String path = loginCommand.execute(request, response);
        String s = null;
        assertNotNull(path);
        assertEquals(path, Pages.INDEX);
//        assertEquals(id, httpSession.getAttribute("X-Auth-Token"));
    }
}
