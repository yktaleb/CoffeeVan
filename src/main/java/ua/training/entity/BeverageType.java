package ua.training.entity;

import java.util.List;
import java.util.Set;

public class BeverageType implements Entity<Long> {
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

    public static final class BeverageTypeBuilder {
        private Long id;
        private String name;
        private List<Beverage> beverages;

        public BeverageTypeBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public BeverageTypeBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public BeverageTypeBuilder setBeverages(List<Beverage> beverages) {
            this.beverages = beverages;
            return this;
        }

        public BeverageType build() {
            BeverageType beverageType = new BeverageType();
            beverageType.setId(id);
            beverageType.setName(name);
            beverageType.setBeverages(beverages);
            return beverageType;
        }
    }
}
