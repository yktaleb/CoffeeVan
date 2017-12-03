package ua.training.controller.command;

import ua.training.controller.FieldBasketFront;
import ua.training.entity.Beverage;
import ua.training.service.BeverageService;
import ua.training.service.VanService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GetFreeVansCommand implements Command {
    private static final String INDEX_PAGE = "WEB-INF/view/index.jsp";
    private static final String BASKET_PAGE = "WEB-INF/view/basket.jsp";
    private static final String BASKET = "basket";

    private final VanService vanService;

    public GetFreeVansCommand(VanService vanService) {
        this.vanService = vanService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        vanService.getFreeVans();
        return BASKET_PAGE;
    }


}
