package ua.training.service;

import ua.training.entity.Van;

import java.util.List;

public interface VanService {
    List<Van> getFreeVans();

    List<Van> getBusyVans();

    void makeVanFree(Long vanId);
}
