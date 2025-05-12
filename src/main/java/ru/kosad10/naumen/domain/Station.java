package ru.kosad10.naumen.domain;

import lombok.Getter;

import java.util.Comparator;

@Getter
public class Station implements Comparable<Station> {
    private Integer clientId;
    private Integer clientCount;

    public Station(Integer clientId, Integer clientCount) {
        this.clientId = clientId;
        this.clientCount = clientCount;
    }

    @Override
    public String toString() {
        return clientId +
                " " + clientCount;
    }

    @Override
    public int compareTo(Station stationFirst) {
        Integer result = Integer.compare(stationFirst.getClientCount(), this.clientCount);
        if (result == 0) {
            result = Integer.compare(this.clientId, stationFirst.getClientId());
        }
        return result;
    }
}
