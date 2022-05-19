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
public class LoginHistory {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("loginTime")
    private LocalDateTime loginTime;

    @JsonProperty("userMail")
    private String userMail;

    @JsonProperty("succeed")
    private boolean succeed;
}