package ru.kosad10.naumen.exception;

public class ListIsEmptyException extends RuntimeException {
    public ListIsEmptyException() {
        super("Список пуст");
    }
}
