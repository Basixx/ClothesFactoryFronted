package com.kodilla.clothesfactory_frontend.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    @JsonProperty("name")
    private String name;

    @JsonProperty("surname")
    private String surname;

    @JsonProperty("phoneNumber")
    private String phoneNumber;

    @JsonProperty("emailAddress")
    private String emailAddress;

    @JsonProperty("password")
    private String password;
}
