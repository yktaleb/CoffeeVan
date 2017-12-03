package ua.training.controller.command;

import ua.training.entity.BeverageOrder;
import ua.training.entity.Order;
import ua.training.service.AdminService;
import ua.training.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AdminCommand implements Command {
    private static final String INDEX_PAGE = "WEB-INF/view/index.jsp";
    private static final String ADMIN_PAGE = "WEB-INF/view/adminPage.jsp";
    private static final String FREE_VANS= "freeVans";
    private static final String ALL_ORDERS_VANS= "allOrders";

    private final AdminService vanService;

    public AdminCommand(AdminService adminService) {
        this.vanService = adminService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().setAttribute(FREE_VANS, vanService.getFreeVans());

        for (Order order : vanService.getAllOrders()) {
            List<BeverageOrder> beverageOrders = order.getBeverageOrders();
//            beverageOrders.
        }
//        request.getSession().setAttribute(ALL_ORDERS_VANS, vanService.getAllOrders());
        return ADMIN_PAGE;
    }


}
