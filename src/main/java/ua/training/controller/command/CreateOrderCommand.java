package ua.training.controller.command;

import ua.training.service.OrderService;
import ua.training.util.constant.general.Pages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static ua.training.util.constant.general.Parameters.*;

public class CreateOrderCommand implements Command {

    private final OrderService orderService;

    public CreateOrderCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        orderService.createOrder(request);
        request.getSession().removeAttribute(BASKET);
        request.getSession().removeAttribute(TOTAL_PRICE);
        request.getSession().removeAttribute(BASKET_FIELDS);
        return Pages.INDEX;
    }
}
