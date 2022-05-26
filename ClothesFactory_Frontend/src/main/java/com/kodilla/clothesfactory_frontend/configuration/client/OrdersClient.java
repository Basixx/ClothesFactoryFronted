package com.kodilla.clothesfactory_frontend.configuration.client;

import com.kodilla.clothesfactory_frontend.domain.Order;
import com.kodilla.clothesfactory_frontend.form.auxiliary.OrderShipment;
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
public class OrdersClient {
    private final RestTemplate restTemplate;
    private static OrdersClient ordersClient;
    private static final Logger LOGGER = LoggerFactory.getLogger(OrdersClient.class);
    private final static String URL = "http://localhost:8080/v1/orders";

    public static OrdersClient getInstance() {
        if (ordersClient == null) {
            ordersClient = new OrdersClient(new RestTemplate());
        }
        return ordersClient;
    }

    public List<Order> getAllOrders() {
        try {
            Order[] ordersResponse = restTemplate.getForObject(
                    URL,
                    Order[].class
            );
            return Optional.ofNullable(ordersResponse)
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList());

        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    public List<Order> getOrderByUser(int userId) {
        try {
            Order[] orderResponse = restTemplate.getForObject(
                    URL + "/byUser/" + userId,
                    Order[].class
            );
            return Optional.ofNullable(orderResponse)
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList());
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    public void createOrder(int userId, OrderShipment orderShipment) {
        restTemplate.postForObject(URL + "/" + userId + "/" + orderShipment, userId, Order.class);
    }

    public void setOrderToPaid(int orderId) {
        try{
            restTemplate.put(
                    URL + "/paid/" + orderId,
                    Order.class
            );
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            throw e;
        }
    }

    public void setOrderToSent(int orderId) {
        try {
            restTemplate.put(
                    URL + "/sent/" + orderId,
                    Order.class
            );
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            throw e;
        }
    }
}