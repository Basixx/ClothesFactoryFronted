package com.kodilla.clothesfactory_frontend.configuration.client;

import com.kodilla.clothesfactory_frontend.domain.ShipmentHistory;
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
public class ShipmentHistoryClient {

    private final RestTemplate restTemplate;
    private static ShipmentHistoryClient shipmentHistoryClient;
    private static final Logger LOGGER = LoggerFactory.getLogger(ShipmentHistoryClient.class);
    private final String url = "http://localhost:8080/v1/shipmentHistory";

    public static ShipmentHistoryClient getInstance() {
        if(shipmentHistoryClient == null) {
            shipmentHistoryClient = new ShipmentHistoryClient(new RestTemplate());
        }
        return shipmentHistoryClient;
    }

    public List<ShipmentHistory> getShipmentHistory() {
        try {
            ShipmentHistory[] shipmentHistoryResponse = restTemplate.getForObject(
                    url,
                    ShipmentHistory[].class
            );
            return Optional.ofNullable(shipmentHistoryResponse)
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList());
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }
}
