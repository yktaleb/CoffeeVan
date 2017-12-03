package ua.training.entity.proxy;

public class ProxyFactory {

    private ProxyFactory() {
    }

    private static class ProxyFactoryHolder {
        private final static ProxyFactory INSTANCE = new ProxyFactory();
    }

    public static ProxyFactory getInstance() {
        return ProxyFactoryHolder.INSTANCE;
    }

    public OrderProxy createOrderProxy() {
        return OrderProxy.getInstance();
    }
}
