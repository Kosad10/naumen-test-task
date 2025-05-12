package ru.kosad10.naumen.service;

import ru.kosad10.naumen.domain.Client;
import ru.kosad10.naumen.domain.DataSet;
import ru.kosad10.naumen.domain.Station;

import java.math.BigDecimal;
import java.util.*;

public class FullScanStationProcessor implements StationProcessor {

    @Override
    public List<Station> process(DataSet dataSet) {
        List<Client> clients = dataSet.getClients();
        List<Station> stations = new ArrayList<>();

        for (int i = 0; i < clients.size(); i++) {
            int countSatationsInRadius = 0;
            for (int j = 0; j < clients.size(); j++) {
                if (i == j) {
                    continue;
                }

                BigDecimal diffX = (clients.get(j).getX().subtract(clients.get(i).getX()));
                BigDecimal diffY = clients.get(j).getY().subtract(clients.get(i).getY());
                BigDecimal distance = diffX.multiply(diffX).add(diffY.multiply(diffY));

                if (dataSet.getRadiusSquared().compareTo(distance) >= 0) {
                    countSatationsInRadius++;
                }
            }

            if (countSatationsInRadius > 0) {
                stations.add(new Station(i, countSatationsInRadius));
            }
        }
        Collections.sort(stations);

        return stations;
    }
}
