package ru.kosad10.naumen.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class DataSet {
    private BigDecimal radiusSquared;
    private List<Client> clients;

    public DataSet () {
        radiusSquared = BigDecimal.ZERO;
        clients = new ArrayList<>();
    }
}
