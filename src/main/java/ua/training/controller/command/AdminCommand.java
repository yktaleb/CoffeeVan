package ua.training.controller.command;

import ua.training.controller.FrontOrder;
import ua.training.entity.BeverageOrder;
import ua.training.entity.Order;
import ua.training.service.AdminService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class AdminCommand implements Command {
    private static final String INDEX_PAGE = "WEB-INF/view/index.jsp";
    private static final String ADMIN_PAGE = "WEB-INF/view/adminPage.jsp";
    private static final String FREE_VANS = "freeVans";
    private static final String BUSY_VANS = "busyVans";
    private static final String ALL_ORDERS = "allOrders";
    private static final String EXCEPTION = "exception";

    private final AdminService adminService;

    public AdminCommand(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute(EXCEPTION);
        request.getSession().setAttribute(FREE_VANS, adminService.getFreeVans());
        request.getSession().setAttribute(BUSY_VANS, adminService.getBusyVans());
        List<FrontOrder> frontOrders = new ArrayList<>();
        List<Order> allOrders = adminService.getAllOrders();
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
        return ADMIN_PAGE;
    }



}
