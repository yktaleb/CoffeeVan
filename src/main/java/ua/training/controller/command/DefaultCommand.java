package ua.training.controller.command;

import ua.training.entity.Beverage;
import ua.training.service.BeverageService;
import ua.training.util.constant.general.Pages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class DefaultCommand implements Command {
    private static final String ALL_BEVERAGE = "allBeverage";
    public static final String QUALITY = "quality";
    public static final String PRICE = "price";

    private BeverageService beverageService;

    public DefaultCommand(BeverageService beverageService) {
        this.beverageService = beverageService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String sortType = request.getParameter("sort");
        List<Beverage> allBeverage = null;
        if (QUALITY.equals(sortType)) {
            allBeverage = beverageService.getSortedByQuality();
        } else if (PRICE.equals(sortType)) {
            allBeverage = beverageService.getSortedByPrice();
        } else {
            allBeverage = beverageService.findAllBeverage();
        }
        request.getSession().setAttribute(ALL_BEVERAGE, allBeverage);
        return Pages.INDEX;
    }
}
