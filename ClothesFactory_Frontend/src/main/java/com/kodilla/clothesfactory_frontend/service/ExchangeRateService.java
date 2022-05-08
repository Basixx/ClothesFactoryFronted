package com.kodilla.clothesfactory_frontend.service;

import com.kodilla.clothesfactory_frontend.configuration.client.ExchangeRateClient;
import com.kodilla.clothesfactory_frontend.domain.ExchangeRate;

public class ExchangeRateService {

    private static ExchangeRateService exchangeRateService;
    private final ExchangeRateClient exchangeRateClient = ExchangeRateClient.getInstance();

    public static ExchangeRateService getInstance() {
        if (exchangeRateService == null) {
            exchangeRateService = new ExchangeRateService();
        }
        return exchangeRateService;
    }

    public ExchangeRate getExchange(String from, String to) {
        return exchangeRateClient.getExchangeRates(from, to);
    }
}