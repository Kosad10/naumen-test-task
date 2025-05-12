package ru.kosad10.naumen.exception;

public class InvalidLineFormatException extends RuntimeException {
    public InvalidLineFormatException(String line) {
        super("Строка не соответствует формату: " + line);
    }
}
