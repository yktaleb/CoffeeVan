package ua.training.controller.command;

import ua.training.entity.Beverage;
import ua.training.service.BeverageService;
import ua.training.service.OrderService;
import ua.training.util.constant.general.Pages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CreateOrderCommand implements Command {

    private final OrderService orderService;

    public CreateOrderCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        orderService.createOrder(request);
        return Pages.INDEX;
    }
}
