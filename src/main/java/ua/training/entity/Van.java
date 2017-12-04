package ua.training.entity;

import ua.training.entity.proxy.VanProxy;

import java.util.List;
import java.util.Set;

public class Van implements Entity<Long> {
    private Long id;
    private String name;
    private Double carryingCapacity;
    private Double maxVolume;
    private VanStatus vanStatus;
    private List<Order> orders;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCarryingCapacity() {
        return carryingCapacity;
    }

    public void setCarryingCapacity(Double carryingCapacity) {
        this.carryingCapacity = carryingCapacity;
    }

    public Double getMaxVolume() {
        return maxVolume;
    }

    public void setMaxVolume(Double maxVolume) {
        this.maxVolume = maxVolume;
    }

    public VanStatus getVanStatus() {
        return vanStatus;
    }

    public void setVanStatus(VanStatus vanStatus) {
        this.vanStatus = vanStatus;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Van{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", carryingCapacity=" + carryingCapacity +
                ", maxVolume=" + maxVolume +
                ", vanStatus=" + vanStatus +
                ", orders=" + orders +
                '}';
    }

    public static final class VanBuilder {
        private Long id;
        private String name;
        private Double carryingCapacity;
        private Double maxVolume;
        private VanStatus vanStatus;
        private List<Order> orders;

        public VanBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public VanBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public VanBuilder setCarryingCapacity(Double carryingCapacity) {
            this.carryingCapacity = carryingCapacity;
            return this;
        }

        public VanBuilder setMaxVolume(Double maxVolume) {
            this.maxVolume = maxVolume;
            return this;
        }

        public VanBuilder setVanStatus(VanStatus vanStatus) {
            this.vanStatus = vanStatus;
            return this;
        }

        public VanBuilder setOrders(List<Order> orders) {
            this.orders = orders;
            return this;
        }

        public Van buildVan() {
            Van van = new Van();
            van.setId(id);
            van.setName(name);
            van.setCarryingCapacity(carryingCapacity);
            van.setMaxVolume(maxVolume);
            van.setVanStatus(vanStatus);
            van.setOrders(orders);
            return van;
        }

        public Van buildVanProxy() {
            VanProxy van = new VanProxy();
            van.setId(id);
            van.setName(name);
            van.setCarryingCapacity(carryingCapacity);
            van.setMaxVolume(maxVolume);
            van.setVanStatus(vanStatus);
            van.setOrders(orders);
            return van;
        }
    }
}
