package ru.kosad10.naumen.persistance;

import ru.kosad10.naumen.domain.Station;
import ru.kosad10.naumen.exception.ListIsEmptyException;
import ru.kosad10.naumen.exception.ProcessFileException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class FileDataWriter implements DataWriter {
    private String filePath;

    public FileDataWriter(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public boolean write(List<Station> stations) {
        if (stations == null) {
            throw new ListIsEmptyException();
        }
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
        return true;
    }
}
