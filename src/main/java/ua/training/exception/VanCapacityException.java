package ua.training.exception;

import ua.training.entity.Van;

public class VanCapacityException extends Exception {
    private Van van;

    public VanCapacityException(Van van, String message) {
        super(message);
        this.van = van;
    }

    public Van getVan() {
        return van;
    }
}
