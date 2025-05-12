package ru.kosad10.naumen.exception;

public class StationsNotFoundException extends RuntimeException {
    public StationsNotFoundException(String message) {
        super(message);
    }
}
