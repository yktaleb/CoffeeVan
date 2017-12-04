package ua.training.controller.command;

import ua.training.service.AdminService;
import ua.training.util.constant.general.Pages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MakeVanFreeCommand implements Command {
    private static final String VAN_ID = "vanId";

    private final AdminService adminService;

    public MakeVanFreeCommand(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Long vanId = Long.valueOf(request.getParameter(VAN_ID));
        adminService.makeVanFree(vanId);
        return Pages.ADMIN;
    }



}
