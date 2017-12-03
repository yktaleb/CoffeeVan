package ua.training.controller.command;

import ua.training.controller.FrontOrder;
import ua.training.entity.BeverageOrder;
import ua.training.entity.Order;
import ua.training.service.AdminService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class SetOrderVanCommand implements Command {
    private static final String INDEX_PAGE = "WEB-INF/view/index.jsp";
    private static final String ADMIN_PAGE = "WEB-INF/view/adminPage.jsp";
    private static final String FREE_VANS = "freeVans";
    private static final String ALL_ORDERS = "allOrders";
    private static final String VAN_ID = "vanId";
    private static final String ORDER_ID = "orderId";

    private final AdminService adminService;

    public SetOrderVanCommand(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Long orderId = Long.valueOf(request.getParameter(ORDER_ID));
        Long vanId = Long.valueOf(request.getParameter(VAN_ID));
        adminService.setOrderVan(orderId, vanId);
        return ADMIN_PAGE;
    }



}
