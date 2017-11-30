package ua.training.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DefaultCommand implements Command {
    public static final String INDEX_PAGE = "WEB-INF/jsp/index.jsp";

    public DefaultCommand() {
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return INDEX_PAGE;
    }
}
