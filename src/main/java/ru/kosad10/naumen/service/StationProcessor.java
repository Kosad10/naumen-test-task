package ru.kosad10.naumen.service;

import ru.kosad10.naumen.domain.DataSet;

import java.util.Map;

public interface ScanStationProcessor {

    Map<Integer, Integer> process(DataSet dataSet);
}
