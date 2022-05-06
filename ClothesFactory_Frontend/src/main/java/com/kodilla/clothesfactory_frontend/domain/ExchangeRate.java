package com.kodilla.clothesfactory_frontend.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExchangeRate {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("fromCurrency")
    private String fromCurrency;

    @JsonProperty("toCurrency")
    private String toCurrency;

    @JsonProperty("currencyRate")
    private BigDecimal currencyRate;
}
