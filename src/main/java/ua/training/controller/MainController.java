package ua.training.controller;

import ua.training.controller.command.CommandCreator;
import ua.training.util.constant.general.Pages;
import ua.training.util.constant.general.Parameters;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.apache.log4j.Logger;

@WebServlet("/")
public class MainController extends HttpServlet {
    private static CommandCreator commandCreator = CommandCreator.getInstance();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request,
                                HttpServletResponse response) throws ServletException, IOException {
        String page;

        try {
            request.getSession().removeAttribute(Parameters.EXCEPTION);
            page = commandCreator.action(request, response);
            request.getSession().setAttribute("page", page);
        } catch (RuntimeException e) {
            page = Pages.ERROR;
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }
}
