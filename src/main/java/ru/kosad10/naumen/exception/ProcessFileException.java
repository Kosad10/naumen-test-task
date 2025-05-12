package ru.kosad10.naumen.exception;

public class ProcessFileException extends RuntimeException{
    public ProcessFileException(Throwable throwable) {
        super("Ошибка при обработке файла", throwable);
    }
}
