package ua.training.controller.command;

import ua.training.controller.util.FieldBasketFront;
import ua.training.entity.Beverage;
import ua.training.service.BeverageService;
import ua.training.util.constant.general.Pages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static ua.training.util.constant.general.Parameters.*;

public class ShowBasketCommand implements Command {

    private final BeverageService beverageService;

    public ShowBasketCommand(BeverageService beverageService) {
        this.beverageService = beverageService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Map<Long, Integer> basket = (Map<Long, Integer>) request.getSession().getAttribute(BASKET);
        if (basket == null) {
            return Pages.INDEX;
        }
        List<FieldBasketFront> basketFields = new ArrayList<>();
        double totalPrice = 0.0;
        for (Long id : basket.keySet()) {
            Beverage beverage = beverageService.findById(id);
            Integer amount = basket.get(id);
            FieldBasketFront field = new FieldBasketFront();
            field.setBeverage(beverage);
            field.setAmount(amount);
            double fieldPrice = beverage.getPrice() * amount;
            field.setPrice(fieldPrice);
            basketFields.add(field);

            totalPrice += fieldPrice;
        }
        request.getSession().setAttribute(TOTAL_PRICE, totalPrice);
        request.getSession().setAttribute(BASKET_FIELDS, basketFields);
        return Pages.BASKET;
    }

}
