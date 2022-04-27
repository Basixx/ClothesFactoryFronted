package com.kodilla.clothesfactory_frontend.configuration.client;

import com.kodilla.clothesfactory_frontend.domain.Cloth;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.util.Optional.ofNullable;

@Component
@RequiredArgsConstructor
public class BackendClient {
    private final RestTemplate restTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(BackendClient.class);



    public List<Cloth> getAllClothes() {
        try {
            Cloth[] clothesResponse = restTemplate.getForObject(
                    "http://localhost:8080/v1/clothes",
                    Cloth[].class
            );
            return Arrays.asList(ofNullable(clothesResponse).orElse(new Cloth[0]));

        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }

}