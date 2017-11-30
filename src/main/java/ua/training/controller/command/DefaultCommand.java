package ua.training.controller.command;

import ua.training.entity.Beverage;
import ua.training.service.BeverageService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class DefaultCommand implements Command {
    private static final String INDEX_PAGE = "WEB-INF/view/index.jsp";
    private static final String ALL_BEVERAGE = "allBeverage";

    private BeverageService beverageService;

    public DefaultCommand(BeverageService beverageService) {
        this.beverageService = beverageService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Beverage> allBeverage = beverageService.findAllBeverage();
        request.getSession().setAttribute(ALL_BEVERAGE, allBeverage);
        return INDEX_PAGE;
    }
}
