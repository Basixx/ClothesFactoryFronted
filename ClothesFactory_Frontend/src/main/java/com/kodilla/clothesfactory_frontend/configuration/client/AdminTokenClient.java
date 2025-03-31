package com.kodilla.clothesfactory_frontend.configuration.client;

import com.kodilla.clothesfactory_frontend.domain.AdminToken;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
public class AdminTokenClient {
    private final RestTemplate restTemplate;
    private static AdminTokenClient adminTokenClient;
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminTokenClient.class);

    private static final String URL = "http://localhost:8080/tokens";

    public static AdminTokenClient getInstance() {
        if (adminTokenClient == null) {
            adminTokenClient = new AdminTokenClient(new RestTemplate());
        }
        return adminTokenClient;
    }

    public Boolean existsToken(String token) {
        try {
            return restTemplate.getForObject(
                    URL + "/" + token,
                    Boolean.class
            );
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    public void createToken() {
        restTemplate.postForObject(
                URL,
                null,
                AdminToken.class
        );
    }

    public void deleteTokens() {
        restTemplate.delete(URL);
    }
}
