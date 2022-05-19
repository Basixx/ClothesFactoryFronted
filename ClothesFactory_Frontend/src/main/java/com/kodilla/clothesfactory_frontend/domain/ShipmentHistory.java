package com.kodilla.clothesfactory_frontend.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class ShipmentHistory {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("shipmentTime")
    private LocalDateTime shipmentTime;

    @JsonProperty("orderId")
    private Long orderId;

    @JsonProperty("userMail")
    private String userMail;

    @JsonProperty("shippingCompany")
    private String shippingCompany;
}
