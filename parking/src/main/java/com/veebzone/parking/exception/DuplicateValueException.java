package com.veebzone.parking.exception;

public class DuplicateValueException  extends RuntimeException{
    public DuplicateValueException() {
        super("Data violates unique value requirements!");
    }
}
