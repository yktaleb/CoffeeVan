package ua.training.controller.command;

import ua.training.service.AdminService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MakeVanFreeCommand implements Command {
    private static final String INDEX_PAGE = "WEB-INF/view/index.jsp";
    private static final String ADMIN_PAGE = "WEB-INF/view/adminPage.jsp";
    private static final String FREE_VANS = "freeVans";
    private static final String ALL_ORDERS = "allOrders";
    private static final String VAN_ID = "vanId";
    private static final String ORDER_ID = "orderId";


    private final AdminService adminService;

    public MakeVanFreeCommand(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Long vanId = Long.valueOf(request.getParameter(VAN_ID));
        adminService.makeVanFree(vanId);
        return ADMIN_PAGE;
    }



}
