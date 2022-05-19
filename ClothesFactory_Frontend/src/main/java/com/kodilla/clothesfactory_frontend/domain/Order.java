package com.kodilla.clothesfactory_frontend.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("paid")
    private boolean paid;

    @JsonProperty("sent")
    private boolean sent;

    @JsonProperty("shipmentCompanyName")
    private String shipmentCompanyName;

    @JsonProperty("shippingPrice")
    private BigDecimal shippingPrice;

    @JsonProperty("deliveryDays")
    private int deliveryDays;


    @JsonProperty("address")
    private String address;

}