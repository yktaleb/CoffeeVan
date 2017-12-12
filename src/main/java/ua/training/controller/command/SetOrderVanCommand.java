package ua.training.controller.command;

import ua.training.exception.VanCapacityException;
import ua.training.service.AdminService;
import ua.training.util.constant.general.Pages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SetOrderVanCommand implements Command {
    private static final String VAN_ID = "vanId";
    private static final String ORDER_ID = "orderId";
    private static final String EXCEPTION = "exception";

    private final AdminService adminService;

    public SetOrderVanCommand(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Long orderId = Long.valueOf(request.getParameter(ORDER_ID));
        Long vanId = Long.valueOf(request.getParameter(VAN_ID));
        try {
            adminService.setOrderVan(orderId, vanId);
        } catch (VanCapacityException e) {
            request.getSession().setAttribute(
                    EXCEPTION, e.getMessage() + "(in" + e.getVan() + ")"
            );
        }
        return Pages.ADMIN;
    }

}
