package com.kodilla.clothesfactory_frontend.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class User {
    private String name;
    private String surname;
    private String phoneNumber;
    private String emailAddress;
    private String password;
}
