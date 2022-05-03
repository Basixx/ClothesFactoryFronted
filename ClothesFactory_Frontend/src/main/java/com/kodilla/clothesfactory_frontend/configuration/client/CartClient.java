package com.kodilla.clothesfactory_frontend.configuration.client;

import com.kodilla.clothesfactory_frontend.domain.Cart;
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
public class CartClient {
    private final RestTemplate restTemplate;
    private static CartClient cartClient;
    private static final Logger LOGGER = LoggerFactory.getLogger(CartClient.class);
    private String url = "http://localhost:8080/v1/carts";

    public static  CartClient getInstance() {
        if (cartClient == null) {
            cartClient = new CartClient(new RestTemplate());
        }
        return cartClient;
    }

    public Cart getCart(int userId) {
        try {
            Cart cartsResponse = restTemplate.getForObject(
                    url + "/" + userId,
                    Cart.class
            );
            return cartsResponse;
//                    .map(Arrays::asList)
//                    .orElse(Collections.emptyList());
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    public void addClothToCart(int cartId, int clothId) {
        restTemplate.put(
                url + "/addCloth/" +  cartId + "/" + clothId,
                Cart.class
        );
    }

    public void deleteClothFromCart(int cartId, int clothId) {
        restTemplate.put(
                url + "/" + cartId + "/" + clothId,
                Cart.class
        );
    }
}
