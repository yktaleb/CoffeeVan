package ua.training.controller.command;

import ua.training.util.constant.general.Pages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class AddToBasketCommand implements Command {
    private static final String BASKET = "basket";
    private static final String ID = "id";
    private static final String AMOUNT = "amount";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Long id = Long.valueOf(request.getParameter(ID));
        int amount = Integer.parseInt(request.getParameter(AMOUNT));
        Map<Long, Integer> basket = (Map<Long, Integer>) request.getSession().getAttribute(BASKET);
        if (basket != null) {
            basket.merge(id, amount, (a, b) -> a + b);
            request.getSession().setAttribute(BASKET, basket);
        } else {
            Map<Long, Integer> map = new HashMap<>();
            map.put(id, amount);
            request.getSession().setAttribute(BASKET, map);
        }
        return Pages.INDEX;
    }
}
