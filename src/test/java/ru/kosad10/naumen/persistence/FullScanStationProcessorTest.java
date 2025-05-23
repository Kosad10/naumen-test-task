package ru.kosad10.naumen.persistence;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.kosad10.naumen.domain.Client;
import ru.kosad10.naumen.domain.DataSet;
import ru.kosad10.naumen.domain.Station;
import ru.kosad10.naumen.service.FullScanStationProcessor;
import ru.kosad10.naumen.service.StationProcessor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class FullScanStationProcessorTest {

    StationProcessor stationProcessor = new FullScanStationProcessor();

    @Test
    @DisplayName("Проверить соответствие клиентов в радиусе")
    void checkClientsInRadius() {
        //Prepsare DataSet

        List<Station> result = prepareDataSet();

        assertEquals(5, result.size());
        assertEquals(0, result.get(0).getClientId());
        assertEquals(2, result.get(0).getClientCount());

        assertEquals(1, result.get(1).getClientId());
        assertEquals(1, result.get(1).getClientCount());

        assertEquals(2, result.get(2).getClientId());
        assertEquals(1, result.get(2).getClientCount());

        assertEquals(3, result.get(3).getClientId());
        assertEquals(1, result.get(3).getClientCount());

        assertEquals(4, result.get(4).getClientId());
        assertEquals(1, result.get(4).getClientCount());
    }

    private List<Station> prepareDataSet() {
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
        List<Station> stations = stationProcessor.process(dataSet);
        return stations;
    }
}
