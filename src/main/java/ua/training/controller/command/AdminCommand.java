package ua.training.controller.command;

import ua.training.controller.util.*;
import ua.training.entity.BeverageOrder;
import ua.training.entity.Order;
import ua.training.service.AdminService;
import ua.training.util.constant.general.Pages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class AdminCommand implements Command {
    private static final String FREE_VANS = "freeVans";
    private static final String BUSY_VANS = "busyVans";
    private static final String ALL_ORDERS = "allOrders";
    private static final String EXCEPTION = "exception";
    private static final String PAGE = "page";
    private static final String NUMBER_OF_PAGES = "numberOfPages";
    private static final int NUMBER_OF_ORDERS_IN_PAGE = 4;

    private final AdminService adminService;

    public AdminCommand(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute(EXCEPTION);
        request.getSession().setAttribute(FREE_VANS, adminService.getFreeVans());
        request.getSession().setAttribute(BUSY_VANS, adminService.getBusyVans());
        int numberOfOrders = adminService.getNumberOfOrders();
        int numberOfPages = numberOfOrders / NUMBER_OF_ORDERS_IN_PAGE;
        if ((numberOfOrders % NUMBER_OF_ORDERS_IN_PAGE) != 0 ) {
            numberOfPages++;
        }
        request.getSession().setAttribute(NUMBER_OF_PAGES, numberOfPages);

        int currentPage;
        int from;
        String pageParameter = request.getParameter(PAGE);
        if (pageParameter != null) {
            currentPage = Integer.valueOf(pageParameter);
            from = currentPage * NUMBER_OF_ORDERS_IN_PAGE;
        } else {
            currentPage = 0;
            from = 0;
        }

        List<FrontOrder> frontOrders = new ArrayList<>();
        List<Order> allOrders = adminService.getAllOrders(NUMBER_OF_ORDERS_IN_PAGE, from);
        for (Order order : allOrders) {
            double totalVolume = 0;
            double totalWeight = 0;
            double totalPrice = 0;
            for (BeverageOrder beverageOrder : order.getBeverageOrders()) {
                Integer amount = beverageOrder.getAmount();
                totalVolume += amount * beverageOrder.getBeverage().getVolume();
                totalWeight += amount * beverageOrder.getBeverage().getWeight();
                totalPrice += amount * beverageOrder.getBeverage().getPrice();
            }
            frontOrders.add(
                    new FrontOrder.FrontOrderBuilder()
                            .setOrder(order)
                            .setBeverageOrder(order.getBeverageOrders())
                            .setTotalVolume(totalVolume)
                            .setTotalWeight(totalWeight)
                            .setTotalPrice(totalPrice)
                            .build()
            );
        }
        request.getSession().setAttribute(ALL_ORDERS, frontOrders);
        return Pages.ADMIN;
    }


}
