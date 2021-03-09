package com.veebzone.parking.exception;

public class NoSlotsLeftException extends RuntimeException {
    public NoSlotsLeftException() {
        super("No more suitable slots left!");
    }
}
