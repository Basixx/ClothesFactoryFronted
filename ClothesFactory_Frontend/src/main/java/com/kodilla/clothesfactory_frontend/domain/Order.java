package com.kodilla.clothesfactory_frontend.domain;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Order {
    private LocalDate orderDate;
    private BigDecimal totalOrderPrice;
}
