package ua.training.service;

import ua.training.service.impl.BeverageServiceImpl;
import ua.training.service.impl.OrderServiceImpl;
import ua.training.service.impl.UserServiceImpl;
import ua.training.service.impl.AdminServiceImpl;

public class ServiceFactory {
    private ServiceFactory() {
    }

    private static class ServiceFactoryHolder {
        private static ServiceFactory instance = new ServiceFactory();
    }

    public static ServiceFactory getInstance() {
        return ServiceFactoryHolder.instance;
    }

    public BeverageService createBeverageService() {
        return BeverageServiceImpl.getInstance();
    }

    public OrderService createOrderService() {
        return OrderServiceImpl.getInstance();
    }

    public UserService createUserService() {
        return UserServiceImpl.getInstance();
    }

    public AdminService createAdminService() {
        return AdminServiceImpl.getInstance(createOrderService());
    }
}
