package ua.training.entity;

import java.util.Set;

public class Van implements Entity<Long> {
    private Long id;
    private String name;
    private Double carryingCapacity;
    private Double maxVolume;
    private VanStatus vanStatus;
    private Set<Order> orders;

    public static final class VanBuilder {
        private Long id;
        private String name;
        private Double carryingCapacity;
        private Double maxVolume;
        private VanStatus vanStatus;
        private Set<Order> orders;

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

        public VanBuilder setOrders(Set<Order> orders) {
            this.orders = orders;
            return this;
        }

        public Van build() {
            Van van = new Van();
            van.orders = this.orders;
            van.carryingCapacity = this.carryingCapacity;
            van.name = this.name;
            van.maxVolume = this.maxVolume;
            van.id = this.id;
            van.vanStatus = this.vanStatus;
            return van;
        }
    }

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

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
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
}
