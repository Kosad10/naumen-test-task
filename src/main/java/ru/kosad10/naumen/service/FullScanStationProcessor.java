package ru.kosad10.naumen.infrastructure;

import ru.kosad10.naumen.domain.Client;
import ru.kosad10.naumen.domain.DataSet;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FullScanStationProcessor {

    public Map<Integer, Integer> process(DataSet dataSet) {
        List<Client> clients = dataSet.getClients();
        Map<Integer, Integer> covered = new HashMap<>();

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
            covered.put(i, countSatationsInRadius);
        }

        return covered;
    }
}
