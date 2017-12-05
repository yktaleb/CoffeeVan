package ua.training.service;

import ua.training.service.impl.*;

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

    public VanService createVanService() {
        return VanServiceImpl.getInstance();
    }

    public AdminService createAdminService() {
        return AdminServiceImpl.getInstance(createOrderService(), createVanService());
    }
}
