package ua.training.entity;

import java.util.Set;

public class VanStatus {
    private Long id;
    private String name;
    private Set<Van> vans;

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

    public Set<Van> getVans() {
        return vans;
    }

    public void setVans(Set<Van> vans) {
        this.vans = vans;
    }
    
    public static final class VanStatusBuilder {
        private Long id;
        private String name;
        private Set<Van> vans;

        public VanStatusBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public VanStatusBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public VanStatusBuilder setVans(Set<Van> vans) {
            this.vans = vans;
            return this;
        }

        public VanStatus build() {
            VanStatus vanStatus = new VanStatus();
            vanStatus.setId(id);
            vanStatus.setName(name);
            vanStatus.setVans(vans);
            return vanStatus;
        }
    }
}
