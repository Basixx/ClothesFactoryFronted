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

    @JsonProperty("id")
    private Long id;

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

    @JsonProperty("street")
    private String street;

    @JsonProperty("streetAndApartmentNumber")
    private String streetAndApartmentNumber;

    @JsonProperty("city")
    private String city;

    @JsonProperty("postCode")
    private String postCode;

    public User(String name, String surname, String phoneNumber, String emailAddress, String password,
                String street, String streetAndApartmentNumber, String city, String postCode) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.password = password;
        this.street = street;
        this.streetAndApartmentNumber = streetAndApartmentNumber;
        this.city = city;
        this.postCode = postCode;
    }
}