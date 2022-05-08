package com.kodilla.clothesfactory_frontend.configuration.client;

import com.kodilla.clothesfactory_frontend.domain.ExchangeRate;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;

@RequiredArgsConstructor
public class ExchangeRateClient {
    private final RestTemplate restTemplate;

    private static ExchangeRateClient exchangeRateClient;

    private static final Logger LOGGER = LoggerFactory.getLogger(ExchangeRateClient.class);
    private final String url = "http://localhost:8080/v1/exchange";

    public static ExchangeRateClient getInstance() {
        if(exchangeRateClient == null) {
            exchangeRateClient = new ExchangeRateClient(new RestTemplate());
        }
        return exchangeRateClient;
    }

    public ExchangeRate getExchangeRates(String from, String to) {

        URI uri = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("from", from)
                .queryParam("to", to)
                .build()
                .encode()
                .toUri();

        try {
            return restTemplate.getForObject(
                    uri,
                    ExchangeRate.class
            );
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }
}