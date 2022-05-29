package com.kodilla.clothesfactory_frontend.configuration.client;

import com.kodilla.clothesfactory_frontend.domain.SignInHistory;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class SignInHistoryClient {

    private final RestTemplate restTemplate;
    private static SignInHistoryClient signInHistoryClient;
    private static final Logger LOGGER = LoggerFactory.getLogger(SignInHistoryClient.class);
    private final static String URL = "http://localhost:8080/v1/signInHistory";

    public static SignInHistoryClient getInstance() {
        if(signInHistoryClient == null) {
            signInHistoryClient = new SignInHistoryClient(new RestTemplate());
        }
        return signInHistoryClient;
    }

    public List<SignInHistory> getSignInHistory() {
        try {
            SignInHistory[] signInHistoryResponse = restTemplate.getForObject(
                    URL,
                    SignInHistory[].class
            );
            return Optional.ofNullable(signInHistoryResponse)
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList());
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }
}
