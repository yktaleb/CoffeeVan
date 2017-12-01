package ua.training.entity;

import java.util.Set;

public class BeverageState implements Entity<Long> {
    private Long id;
    private String name;
    private Set<Beverage> beverages;

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

    public Set<Beverage> getBeverages() {
        return beverages;
    }

    public void setBeverages(Set<Beverage> beverages) {
        this.beverages = beverages;
    }

    public static final class BeverageStateBuilder {
        private Long id;
        private String name;
        private Set<Beverage> beverages;

        public BeverageStateBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public BeverageStateBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public BeverageStateBuilder setBeverages(Set<Beverage> beverages) {
            this.beverages = beverages;
            return this;
        }

        public BeverageState build() {
            BeverageState beverageState = new BeverageState();
            beverageState.setId(id);
            beverageState.setName(name);
            beverageState.setBeverages(beverages);
            return beverageState;
        }
    }
}
