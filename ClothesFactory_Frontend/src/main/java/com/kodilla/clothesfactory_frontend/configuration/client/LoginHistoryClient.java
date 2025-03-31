package com.kodilla.clothesfactory_frontend.configuration.client;

import com.kodilla.clothesfactory_frontend.domain.LoginHistory;
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
public class LoginHistoryClient {

    private final RestTemplate restTemplate;
    private static LoginHistoryClient loginHistoryClient;
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginHistoryClient.class);
    private final static String URL = "http://localhost:8080/loginHistory";

    public static LoginHistoryClient getInstance() {
        if(loginHistoryClient == null) {
            loginHistoryClient = new LoginHistoryClient(new RestTemplate());
        }
        return loginHistoryClient;
    }

    public List<LoginHistory> getLoginHistory() {
        try {
            LoginHistory[] loginHistoryResponse = restTemplate.getForObject(
                    URL,
                    LoginHistory[].class
            );
            return Optional.ofNullable(loginHistoryResponse)
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList());
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }
}
