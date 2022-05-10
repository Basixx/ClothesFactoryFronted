package com.kodilla.clothesfactory_frontend.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kodilla.clothesfactory_frontend.form.auxiliary.OrderShipment;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class Order {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("orderDate")
    private LocalDate orderDate;

    @JsonProperty("totalOrderPrice")
    private BigDecimal totalOrderPrice;

    @JsonProperty("shipment")
    private OrderShipment orderShipment;

    @JsonProperty("paid")
    private boolean paid;

    @JsonProperty("sent")
    private boolean sent;

    @JsonProperty("address")
    private String address;
}