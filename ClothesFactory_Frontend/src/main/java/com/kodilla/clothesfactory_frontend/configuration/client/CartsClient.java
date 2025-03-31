package com.kodilla.clothesfactory_frontend.configuration.client;

import com.kodilla.clothesfactory_frontend.domain.Cart;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
public class CartsClient {
    private final RestTemplate restTemplate;
    private static CartsClient cartsClient;
    private static final Logger LOGGER = LoggerFactory.getLogger(CartsClient.class);
    private final static String URL = "http://localhost:8080";

    public static CartsClient getInstance() {
        if (cartsClient == null) {
            cartsClient = new CartsClient(new RestTemplate());
        }
        return cartsClient;
    }

    public Cart getUserCart(int userId) {
        try {
            return restTemplate.getForObject(
                    URL + "/users/" + userId + "/cart",
                    Cart.class
            );
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    public void addClothToCart(int cartId, int clothId) {
        restTemplate.put(
                URL + "/carts/" +  cartId + "/clothes/" + clothId,
                Cart.class
        );
    }

    public void deleteClothFromCart(int cartId, int clothId) {
        restTemplate.delete(
                URL + "/carts/" + cartId + "/clothes/" + clothId,
                Cart.class
        );
    }

}
