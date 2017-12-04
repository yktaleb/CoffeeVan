package ua.training.entity;

import java.util.List;

public class BeverageQuality implements Entity<Long> {
    private Long id;
    private String name;
    private List<Beverage> beverages;

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

    public List<Beverage> getBeverages() {
        return beverages;
    }

    public void setBeverages(List<Beverage> beverages) {
        this.beverages = beverages;
    }

    public static final class BeverageQualityBuilder {
        private Long id;
        private String name;
        private List<Beverage> beverages;

        public BeverageQualityBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public BeverageQualityBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public BeverageQualityBuilder setBeverages(List<Beverage> beverages) {
            this.beverages = beverages;
            return this;
        }

        public BeverageQuality build() {
            BeverageQuality beverageQuality = new BeverageQuality();
            beverageQuality.setId(id);
            beverageQuality.setName(name);
            beverageQuality.setBeverages(beverages);
            return beverageQuality;
        }
    }
}
