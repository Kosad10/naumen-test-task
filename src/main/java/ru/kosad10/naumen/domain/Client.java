package ru.kosad10.naumen.domain;

import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {
    private BigDecimal x;
    private BigDecimal y;
}
