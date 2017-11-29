package ua.training.entity;

import java.util.Set;

public class Van {
    private Long id;
    private String name;
    private Double carryingCapacity;
    private Double maxVolume;
    private VanStatus vanStatus;
    private Set<Order> orders;
}
