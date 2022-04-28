package com.kodilla.clothesfactory_frontend.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kodilla.clothesfactory_frontend.form.auxiliary.ClothColor;
import com.kodilla.clothesfactory_frontend.form.auxiliary.ClothFashion;
import com.kodilla.clothesfactory_frontend.form.auxiliary.ClothFont;
import com.kodilla.clothesfactory_frontend.form.auxiliary.ClothSize;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class Cloth {

    @JsonProperty("fashion")
    private ClothFashion fashion;

    @JsonProperty("color")
    private ClothColor color;

    @JsonProperty("print")
    private String print;

    @JsonProperty("font")
    private ClothFont font;

    @JsonProperty("printColor")
    private ClothColor printColor;

    @JsonProperty("size")
    private ClothSize size;

    @JsonProperty("quantity")
    private int quantity;

    @JsonProperty("price")
    private BigDecimal price;
}
