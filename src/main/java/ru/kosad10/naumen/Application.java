package ru.kosad10.naumen;

import ru.kosad10.naumen.domain.DataSet;
import ru.kosad10.naumen.persistance.FileDataWriter;
import ru.kosad10.naumen.service.FullScanStationProcessor;
import ru.kosad10.naumen.persistance.DataReader;
import ru.kosad10.naumen.persistance.FileDataReader;


public class Application {
    public static void main(String[] args) {
        DataReader dataReader = new FileDataReader("input.txt");
        DataSet dataSet = dataReader.read();

        FullScanStationProcessor processor = new FullScanStationProcessor();
        processor.process(dataSet);

        FileDataWriter fileDataWriter = new FileDataWriter("output.txt");
        fileDataWriter.write(processor.process(dataSet));
    }
}