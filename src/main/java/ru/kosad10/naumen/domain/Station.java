package ru.kosad10.naumen.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Станция, содержит номер клиента у которого выгодней всего строить станцию и
 * количество обслуживаемых этой станцией клиентов
 */
@AllArgsConstructor
@Getter
public class Station implements Comparable<Station> {
    private Integer clientId;
    private Integer clientCount;

    /**
     * Метод, который определяет форму записи данных в выходящий файл
     * @return возвращает номер клиента и количество обслуживаемых им станций
     */

    @Override
    public String toString() {
        return clientId +
                " " + clientCount;
    }

    /**
     * Метод, который определяет порядок сортировки:
     * сначала сортировка по количеству станций в порядке убывания,
     * в случае, если количество станций равное - сортировка по номеру клиента в порядке возрастания
     * @param stationFirst объект для сравнения
     * @return возвращает резуальтат сравнения
     */
    @Override
    public int compareTo(Station stationFirst) {
        Integer result = Integer.compare(stationFirst.getClientCount(), this.clientCount);
        if (result == 0) {
            result = Integer.compare(this.clientId, stationFirst.getClientId());
        }
        return result;
    }
}
