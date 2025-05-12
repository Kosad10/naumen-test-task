package ru.kosad10.naumen.service;

import ru.kosad10.naumen.domain.DataSet;
import ru.kosad10.naumen.domain.Station;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface StationProcessor {

    List<Station> process(DataSet dataSet);
}
