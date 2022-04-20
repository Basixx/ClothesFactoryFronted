package com.kodilla.clothesfactory_frontend.domain;

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
public class Cloth {
    private ClothFashion fashion;
    private ClothColor color;
    private String print;
    private ClothFont font;
    private ClothColor printColor;
    private ClothSize size;
    private int quantity;
   // private BigDecimal price;
}
