package com.kodilla.clothesfactory_frontend.configuration.client;

import com.kodilla.clothesfactory_frontend.domain.Cloth;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RequiredArgsConstructor
public class ClothesClient {

    private final RestTemplate restTemplate;
    private static ClothesClient clothesClient;
    private static final Logger LOGGER = LoggerFactory.getLogger(ClothesClient.class);
    private final static String URL = "http://localhost:8080";

    public static ClothesClient getInstance() {
        if (clothesClient == null) {
            clothesClient = new ClothesClient(new RestTemplate());
        }
        return clothesClient;
    }

    public List<Cloth> getAllClothes() {
        try {
            Cloth[] clothesResponse = restTemplate.getForObject(
                    URL + "/clothes",
                    Cloth[].class
            );
            return Optional.ofNullable(clothesResponse)
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList());

        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    public List<Cloth> getClothesFromUserCart(int userId) {
        try {
            Cloth[] clothesResponse = restTemplate.getForObject(
                    URL + "/users/" + userId + "/cart/clothes",
                    Cloth[].class
            );
            return Optional.ofNullable(clothesResponse)
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList());

        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    public List<Cloth> getClothesFromOrder(int orderId) {
        try {
            Cloth[] clothesResponse = restTemplate.getForObject(
                    URL + "/orders/" + orderId + "/clothes",
                    Cloth[].class
            );
            return Optional.ofNullable(clothesResponse)
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList());

        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    public Cloth createCloth(Cloth newCloth) {
        try {
            return restTemplate.postForObject(
                    URL + "/clothes",
                    newCloth,
                    Cloth.class
            );
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            throw e;
        }
    }

    public void updateCloth(int clothId, Cloth updatedCloth) {
        try {
            restTemplate.put(
                    URL + "/clothes/" + clothId,
                    updatedCloth
            );
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            throw e;
        }
    }
}