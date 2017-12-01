package ua.training.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class AddToBasketCommand implements Command {
    private static final String INDEX_PAGE = "WEB-INF/view/index.jsp";
    private static final String BASKET = "basket";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Long id = Long.valueOf(request.getParameter("id"));
        int amount = Integer.parseInt(request.getParameter("amount"));
        Map<Long, Integer> basket = (Map<Long, Integer>) request.getSession().getAttribute(BASKET);
        if (basket != null) {
            Integer previousAmount = basket.get(id);
            if (previousAmount != null) {
                basket.put(id, previousAmount + amount);
            } else {
                basket.put(id, amount);
            }
            request.getSession().setAttribute(BASKET, basket);
        } else {
            Map<Long, Integer> map = new HashMap<>();
            map.put(id, amount);
            request.getSession().setAttribute(BASKET, map);
        }
        return INDEX_PAGE;
    }
}
