package ru.kosad10.naumen.domain;

import lombok.*;

import java.math.BigDecimal;

/**
 * Клиент, содержит значения координат x и y
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {
    private BigDecimal x;
    private BigDecimal y;
}
