package ua.training.service;

import ua.training.entity.Van;

import java.util.Set;

public interface VanService {
    Set<Van> getFreeVans();
}
