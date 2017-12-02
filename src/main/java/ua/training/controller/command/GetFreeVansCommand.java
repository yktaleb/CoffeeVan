package ua.training.controller.command;

import ua.training.controller.FieldBasketFront;
import ua.training.entity.Beverage;
import ua.training.service.BeverageService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GetFreeVansCommand implements Command {
    private static final String INDEX_PAGE = "WEB-INF/view/index.jsp";
    private static final String BASKET_PAGE = "WEB-INF/view/basket.jsp";
    private static final String BASKET = "basket";

    private final BeverageService beverageService;

    public GetFreeVansCommand(BeverageService beverageService) {
        this.beverageService = beverageService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Map<Long, Integer> basket = (Map<Long, Integer>) request.getSession().getAttribute(BASKET);
        List<FieldBasketFront> basketFields = new ArrayList<>();
        double totalPrice = 0.0;
        for (Long id : basket.keySet()) {
            Beverage beverage = beverageService.findById(id).get();
            Integer amount = basket.get(id);
            FieldBasketFront field = new FieldBasketFront();
            field.setBeverage(beverage);
            field.setAmount(amount);
            double fieldPrice = beverage.getPrice() * amount;
            field.setPrice(fieldPrice);
            basketFields.add(field);

            totalPrice += fieldPrice;
        }
        request.getSession().setAttribute("totalPrice", totalPrice);
        request.getSession().setAttribute("basketFields", basketFields);
        return BASKET_PAGE;
    }


}
