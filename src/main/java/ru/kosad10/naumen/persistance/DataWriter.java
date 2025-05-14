package ru.kosad10.naumen.persistance;

import ru.kosad10.naumen.domain.Station;

import java.util.List;
import java.util.Map;

public interface DataWriter {

    void write(List<Station> stations);
}
