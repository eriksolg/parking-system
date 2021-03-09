package com.veebzone.parking.exception;

public class InUseException extends RuntimeException {
    public InUseException() {
        super("Action cannot be performed - instance referenced elsewhere!");
    }
}