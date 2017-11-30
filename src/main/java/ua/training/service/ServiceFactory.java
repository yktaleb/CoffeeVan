package ua.training.service;

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
}
