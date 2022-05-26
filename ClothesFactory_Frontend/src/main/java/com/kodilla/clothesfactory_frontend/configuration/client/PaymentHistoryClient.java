package com.kodilla.clothesfactory_frontend.configuration.client;

import com.kodilla.clothesfactory_frontend.domain.PaymentHistory;
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
public class PaymentHistoryClient {

    private final RestTemplate restTemplate;
    private static PaymentHistoryClient paymentHistoryClient;
    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentHistoryClient.class);
    private final static String URL = "http://localhost:8080/v1/paymentHistory";

    public static PaymentHistoryClient getInstance() {
        if(paymentHistoryClient == null) {
            paymentHistoryClient = new PaymentHistoryClient(new RestTemplate());
        }
        return paymentHistoryClient;
    }

    public List<PaymentHistory> getPaymentHistory() {
        try {
            PaymentHistory[] paymentHistoryResponse = restTemplate.getForObject(
                    URL,
                    PaymentHistory[].class
            );
            return Optional.ofNullable(paymentHistoryResponse)
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList());
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }
}
