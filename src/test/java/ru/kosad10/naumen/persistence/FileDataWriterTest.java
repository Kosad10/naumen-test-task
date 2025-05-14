package ru.kosad10.naumen.persistence;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.kosad10.naumen.domain.Station;
import ru.kosad10.naumen.exception.ProcessFileException;
import ru.kosad10.naumen.persistance.DataWriter;
import ru.kosad10.naumen.persistance.FileDataWriter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileDataWriterTest {

    @Test
    @DisplayName("Выброси исключение если файл не записан")
    void shouldThrowExceptionIfFileIsNotWritten() {
        DataWriter writer = new FileDataWriter("");
        List<Station> stations = new ArrayList<>();
        stations.add(new Station(0,0));

        assertThrows(ProcessFileException.class, () -> writer.write(stations));
    }

    //тест на создание файла, если он не создан
    @Test
    @DisplayName("Создай файл если он отсутствует")
    void creatFileIfTheyNotExist() {
        File file = new File("test/output-integer-test.txt");
        List<Station> stations = new ArrayList<>();
        stations.add(new Station(0,9));
        if (file.exists()) {
            file.delete();
        }
        assertFalse(file.exists());

       DataWriter dataWriter = new FileDataWriter("test/output-integer-test.txt");
       dataWriter.write(stations);

        assertTrue(file.exists());
        assertTrue(file.length() > 0);
    }


    @Test
    @DisplayName("Запиши в файл не больше 10 строк")
    void writeDataNoMoreThanTenLines() throws IOException {
        DataWriter dataWriter = new FileDataWriter("test/output-integer-test.txt");
        List<Station> stations = new ArrayList<>();
        for (int i = 1; i < 12; i++) {
            stations.add(new Station(i, i * 2));
        }

        dataWriter.write(stations);
        List<String> lines = Files.readAllLines(Paths.get("test/output-integer-test.txt"));

        assertTrue(lines.size() == 10);
        for (int i = 0; i < lines.size(); i++) {
            assertEquals(stations.get(i).toString(), lines.get(i));
        }
    }
}
