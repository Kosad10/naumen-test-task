package ru.kosad10.naumen.exception;

public class FileNotFoundException extends RuntimeException {
    public FileNotFoundException(String filePath) {
        super(filePath + " не существует");
    }
}
