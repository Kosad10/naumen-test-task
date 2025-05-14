package ru.kosad10.naumen.persistance;

import lombok.AllArgsConstructor;
import ru.kosad10.naumen.domain.Station;
import ru.kosad10.naumen.exception.ProcessFileException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

@AllArgsConstructor
public class FileDataWriter implements DataWriter {
    private String filePath;

    /**
     * Записывает в файл по пути filePath не более десяти строк
     * <p>
     * Каждая строка содержит номер клиента, у которого выгодней всего строить станцию и
     * количество обслуживаемых этой станцией клиентов
     *
     * @param stations список {@link Station станций}
     * @throws ProcessFileException при возникновении ошибки обработки файла
     */

    @Override
    public void write(List<Station> stations) {
        File outputFile = new File(filePath);

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile, false));
            for (int i = 0; i < stations.size() && i < 10; i++) {
                writer.write(String.valueOf(stations.get(i)));
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            throw new ProcessFileException(e);
        }
    }
}
