package ru.kosad10.naumen.persistence;

import lombok.NoArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.kosad10.naumen.domain.Client;
import ru.kosad10.naumen.domain.DataSet;
import ru.kosad10.naumen.domain.Station;
import ru.kosad10.naumen.exception.ProcessFileException;
import ru.kosad10.naumen.exception.ListIsEmptyException;
import ru.kosad10.naumen.persistance.DataReader;
import ru.kosad10.naumen.persistance.DataWriter;
import ru.kosad10.naumen.persistance.FileDataReader;
import ru.kosad10.naumen.persistance.FileDataWriter;
import ru.kosad10.naumen.service.FullScanStationProcessor;
import ru.kosad10.naumen.service.StationProcessor;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@NoArgsConstructor
class FileDataWriterTest {
    private DataWriter dataWriter;
    List<Station> stations;
    String filePath;

    @Test
    @DisplayName("Выброси исключение если список станций пуст")
    void shouldThrowExceptionIfStationsNotFound() {
        DataWriter writer = new FileDataWriter("");
        List<Station> stations = null;

        assertThrows(ListIsEmptyException.class, () -> writer.write(stations));
    }

    @Test
    @DisplayName("Выброси исключение если файл не записан")
    void shouldThrowExceptionIfFileIsNotWritten() {
        DataWriter writer = new FileDataWriter("");
        List<Station> stations = getStations();

        assertThrows(ProcessFileException.class, () -> writer.write(stations));
    }

    //тест на создание файла, если он не создан
    @Test
    @DisplayName("Создание файла для записи если он не создан")
    void checkCreatingFileIfTheyNotExist() {
        filePath = "test/output-integer-test.txt";
        File file = new File(filePath);
        stations = getStations();
        if (file.exists()) {
            file.delete();
        }
        dataWriter = new FileDataWriter(filePath);

        assertFalse(file.exists());
        boolean doWriting = dataWriter.write(stations);

        assertTrue(doWriting);
        assertTrue(file.exists());
        assertTrue(file.length() > 0);
    }


    @Test
    @DisplayName("Проверка записи в файл")
    void checkWritingData() throws IOException {
        Path file = Files.createTempFile("output-integer-test", ".txt");
        filePath = "test/output-integer-test.txt";
        dataWriter = new FileDataWriter(filePath);
        List<Station> stations1 = new ArrayList<>();
        for (int i = 1; i < 12; i++) {
            stations1.add(new Station(i, i * 2));
        }

        dataWriter.write(stations1);
        DataReader reader = new FileDataReader(filePath);
        List<String> lines = Files.readAllLines(file);

        assertTrue(lines.size()<10);
        for (int i = 0; i < lines.size(); i++) {
            assertEquals(stations1.get(i).toString(), lines.get(i));
        }
    }

    private static List<Station> getStations() {
        Client cl1 = new Client(BigDecimal.valueOf(0), BigDecimal.valueOf(0));
        Client cl2 = new Client(BigDecimal.valueOf(2), BigDecimal.valueOf(-2));
        Client cl3 = new Client(BigDecimal.valueOf(5), BigDecimal.valueOf(3));
        Client cl4 = new Client(BigDecimal.valueOf(-2), BigDecimal.valueOf(2));
        Client cl5 = new Client(BigDecimal.valueOf(5), BigDecimal.valueOf(1));
        List<Client> clients = new ArrayList<>(Arrays.asList(cl1, cl2, cl3, cl4, cl5));

        DataSet dataSet = new DataSet();
        dataSet.setRadiusSquared(BigDecimal.valueOf(9));
        dataSet.setClients(clients);

        StationProcessor stationProcessor = new FullScanStationProcessor();
        return stationProcessor.process(dataSet);
    }
}
