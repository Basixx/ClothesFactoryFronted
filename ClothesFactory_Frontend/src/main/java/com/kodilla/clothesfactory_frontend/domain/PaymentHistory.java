package com.kodilla.clothesfactory_frontend.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentHistory {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("paymentTime")
    private LocalDateTime paymentTime;

    @JsonProperty("orderId")
    private Long orderId;

    @JsonProperty("userMail")
    private String userMail;

    @JsonProperty("price")
    private BigDecimal price;
}