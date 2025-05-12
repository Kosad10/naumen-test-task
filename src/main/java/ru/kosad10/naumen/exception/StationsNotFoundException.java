package ru.kosad10.naumen.exception;

public class ListStationsIsEmptyException extends RuntimeException {
    public ListStationsIsEmptyException(String message) {
        super(message);
    }
}
