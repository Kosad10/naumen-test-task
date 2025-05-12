package ru.kosad10.naumen.persistance;

import ru.kosad10.naumen.domain.Client;
import ru.kosad10.naumen.domain.DataSet;
import ru.kosad10.naumen.exception.FileNotFoundException;
import ru.kosad10.naumen.exception.InvalidLineFormatException;
import ru.kosad10.naumen.exception.ProcessFileException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;

public class FileDataReader implements DataReader {
    private String filePath;

    public FileDataReader(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public DataSet read() {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException(filePath);
        }
        DataSet dataSet = new DataSet();

        try (BufferedReader reader = new BufferedReader(new FileReader(file, StandardCharsets.UTF_8))) {
           BigDecimal[] firstLine = parseLine(reader.readLine());
           Integer n = firstLine[0].intValue();
           BigDecimal r = firstLine[1];
           dataSet.setRadiusSquared(r.multiply(r));

            for (int i = 0; i < n; i++) {
                BigDecimal[] line = parseLine(reader.readLine());
                Client client = new Client();
                client.setX(line[0]);
                client.setY(line[1]);
                dataSet.getClients().add(client);
            }
        } catch (IOException e) {
            throw new ProcessFileException(e);
        }

        return dataSet;
    }

    private BigDecimal[] parseLine(String line) {
        if(line == null) {
            throw new InvalidLineFormatException(line);
        }
        String[] parts = line.trim().split("\\s+");
        if (parts.length != 2) {
            throw new InvalidLineFormatException(line);
        }
        BigDecimal x = new BigDecimal(parts[0]);
        BigDecimal y = new BigDecimal(parts[1]);
        return new BigDecimal[]{x, y};
    }
}
