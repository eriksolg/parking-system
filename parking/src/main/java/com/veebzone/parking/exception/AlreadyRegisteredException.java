package com.veebzone.parking.exception;

public class AlreadyRegisteredException extends RuntimeException {
    public AlreadyRegisteredException() {
        super("Already registered!");
    }
}