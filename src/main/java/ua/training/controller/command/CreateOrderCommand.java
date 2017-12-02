package ua.training.controller.command;

import ua.training.entity.Beverage;
import ua.training.service.BeverageService;
import ua.training.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CreateOrderCommand implements Command {
    private static final String INDEX_PAGE = "WEB-INF/view/index.jsp";
    private static final String ALL_BEVERAGE = "allBeverage";

    private final OrderService orderService;

    public CreateOrderCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        orderService.createOrder(request);
        return INDEX_PAGE;
    }
}
