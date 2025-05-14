package ru.kosad10.naumen.persistance;

import lombok.AllArgsConstructor;
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

@AllArgsConstructor
public class FileDataReader implements DataReader {

    private String filePath;

    /**
     * Чтение входящего файла по пути filePath, файл должен содержать:
     * <p>
     * В первой строке 2 числа через пробел:
     * <ul>
     *  <li>количество клиентов 1 ≤ N ≤ (231-1)</li>
     *  <li>значение параметра 0 < R ≤ (1.7*10100)</li>
     * </ul>
     * Начиная со второй строки следует ровно N строк, в которых
     * написано два числа — координаты (-1.7*10100) < Xi,Yi ≤ (1.7*10100)
     * i-го клиента. Нумерация ведется с нуля.
     *
     * @return {@link DataSet}, квадрат радиуса обслуживания станции и список {@link Client клиентов}
     * @throws FileNotFoundException - при отсутствии файла по указанному filePath
     * @throws ProcessFileException - при ошибке обработки файла
     * @throws InvalidLineFormatException - при некорректном формате строки
     */
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
