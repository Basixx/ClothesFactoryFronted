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

    private final String url = "http://localhost:8080/v1/token";

    public static AdminTokenClient getInstance() {
        if (adminTokenClient == null) {
            adminTokenClient = new AdminTokenClient(new RestTemplate());
        }
        return adminTokenClient;
    }

    public Boolean existsToken(String token) {
        try {
            return restTemplate.getForObject(
                    url + "/" + token,
                    Boolean.class
            );
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    public void createToken() {
        restTemplate.postForObject(
                url,
                null,
                AdminToken.class
        );
    }

    public void deleteTokens() {
        restTemplate.delete(url);
    }
}