package ua.training.entity;

import java.util.List;

public class Beverage implements Entity<Long> {
    private Long id;
    private String name;
    private Double price;
    private Double weight;
    private Double volume;
    private BeverageType type;
    private BeverageState state;
    private BeverageQuality quality;
    private List<BeverageOrder> beverageOrders;

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public BeverageType getType() {
        return type;
    }

    public void setType(BeverageType type) {
        this.type = type;
    }

    public BeverageState getState() {
        return state;
    }

    public void setState(BeverageState state) {
        this.state = state;
    }

    public BeverageQuality getQuality() {
        return quality;
    }

    public void setQuality(BeverageQuality quality) {
        this.quality = quality;
    }

    public List<BeverageOrder> getBeverageOrders() {
        return beverageOrders;
    }

    public void setBeverageOrders(List<BeverageOrder> beverageOrders) {
        this.beverageOrders = beverageOrders;
    }

    public static final class BeverageBuilder {
        private Long id;
        private String name;
        private Double price;
        private Double weight;
        private Double volume;
        private BeverageType type;
        private BeverageState state;
        private BeverageQuality quality;
        private List<BeverageOrder> beverageOrders;

        public BeverageBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public BeverageBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public BeverageBuilder setPrice(Double price) {
            this.price = price;
            return this;
        }

        public BeverageBuilder setWeight(Double weight) {
            this.weight = weight;
            return this;
        }

        public BeverageBuilder setVolume(Double volume) {
            this.volume = volume;
            return this;
        }

        public BeverageBuilder setType(BeverageType type) {
            this.type = type;
            return this;
        }

        public BeverageBuilder setState(BeverageState state) {
            this.state = state;
            return this;
        }

        public BeverageBuilder setQuality(BeverageQuality quality) {
            this.quality = quality;
            return this;
        }

        public BeverageBuilder setBeverageOrders(List<BeverageOrder> beverageOrders) {
            this.beverageOrders = beverageOrders;
            return this;
        }

        public Beverage build() {
            Beverage beverage = new Beverage();
            beverage.setId(id);
            beverage.setName(name);
            beverage.setPrice(price);
            beverage.setWeight(weight);
            beverage.setVolume(volume);
            beverage.setType(type);
            beverage.setState(state);
            beverage.setQuality(quality);
            beverage.setBeverageOrders(beverageOrders);
            return beverage;
        }
    }
}
