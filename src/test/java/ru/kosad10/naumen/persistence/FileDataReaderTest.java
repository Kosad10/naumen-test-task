package ru.kosad10.naumen.persistence;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.kosad10.naumen.domain.DataSet;
import ru.kosad10.naumen.exception.FileNotFoundException;
import ru.kosad10.naumen.exception.InvalidLineFormatException;
import ru.kosad10.naumen.persistance.DataReader;
import ru.kosad10.naumen.persistance.FileDataReader;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class FileDataReaderTest {

    @Test
    @DisplayName("Выкинуть исключение, если файл не найден")
    void shouldThrowExceptionIfFileNotFound() {
       DataReader reader = new FileDataReader("not-existed-file.txt");

       assertThrows(FileNotFoundException.class, () -> reader.read());
    }

    @Test
    @DisplayName("Выкинуть исключение, если строка пустая")
    void shouldThrowExceptionIfLineIsEmpty() {
        DataReader reader = new FileDataReader("test/input-empty.txt");

        assertThrows(InvalidLineFormatException.class, () -> reader.read());
    }

    @Test
    @DisplayName("Выкинуть исключение, если в строке 1 число")
    void shouldThrowExceptionIfLineHasOneNumber() {
        DataReader reader = new FileDataReader("test/input-line-with-one-number.txt");

        assertThrows(InvalidLineFormatException.class, () -> reader.read());
    }

    @Test
    @DisplayName("Выкинуть исключение, если в строке более 2 чисел")
    void shouldThrowExceptionIfLineHasMoreThanTwoNumber() {
        DataReader reader = new FileDataReader("test/input-more-than-two-numbers.txt");

        assertThrows(InvalidLineFormatException.class, () -> reader.read());
    }

    @Test
    @DisplayName("Выкинуть исключение, если в строке не число")
    void shouldThrowExceptionIfLineHasNotNumber() {
        DataReader reader = new FileDataReader("test/input-not-number.txt");

        assertThrows(NumberFormatException.class, () -> reader.read());
    }

    @Test
    @DisplayName("Выкинуть исключение, если в файле не достаточно данных")
    void shouldThrowExceptionIfDataIsNotEnough() {
        DataReader reader = new FileDataReader("test/input-data-is-not-enough.txt");

        assertThrows(NumberFormatException.class, () -> reader.read());
    }

    @Test
    @DisplayName("Прочитать дата сет из файла с целочисленными значениями")
    void readDataSetWithIntegerNumbers() {
        DataReader dataReader = new FileDataReader("test/input-integer.txt");
        DataSet dataSet = dataReader.read();

        assertNotNull(dataSet);
        assertEquals(3, dataSet.getClients().size());

        assertEquals(new BigDecimal("9"), dataSet.getRadiusSquared());

        assertEquals(new BigDecimal("0"), dataSet.getClients().get(0).getX());
        assertEquals(new BigDecimal("0"), dataSet.getClients().get(0).getY());

        assertEquals(new BigDecimal("2"), dataSet.getClients().get(1).getX());
        assertEquals(new BigDecimal("-2"), dataSet.getClients().get(1).getY());

        assertEquals(new BigDecimal("5"), dataSet.getClients().get(2).getX());
        assertEquals(new BigDecimal("3"), dataSet.getClients().get(2).getY());
    }

    @Test
    @DisplayName("Прочитать дата сет из файла с дробными значениями")
    void readDataSetWithBigDecimalNumbers() {
        DataReader dataReader = new FileDataReader("test/input-short-big-decimal.txt");
        DataSet dataSet = dataReader.read();

        assertNotNull(dataSet);
        assertEquals(4, dataSet.getClients().size());

        assertEquals(new BigDecimal("9.000000000000"), dataSet.getRadiusSquared());

        assertEquals(new BigDecimal("3.168070"), dataSet.getClients().get(0).getX());
        assertEquals(new BigDecimal("1.752490"), dataSet.getClients().get(0).getY());

        assertEquals(new BigDecimal("0.500730"), dataSet.getClients().get(1).getX());
        assertEquals(new BigDecimal("6.436580"), dataSet.getClients().get(1).getY());

        assertEquals(new BigDecimal("0.089300"), dataSet.getClients().get(2).getX());
        assertEquals(new BigDecimal("0.112720"), dataSet.getClients().get(2).getY());

        assertEquals(new BigDecimal("2.275440"), dataSet.getClients().get(3).getX());
        assertEquals(new BigDecimal("7.508780"), dataSet.getClients().get(3).getY());
    }

}
