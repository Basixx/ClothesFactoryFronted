package com.kodilla.clothesfactory_frontend.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Login {
    private String emailAddress;
    private String password;
}
