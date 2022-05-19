package com.kodilla.clothesfactory_frontend.service;

import com.kodilla.clothesfactory_frontend.configuration.client.KanyeQuoteClient;

public class KanyeQuoteService {

    private static KanyeQuoteService kanyeQuoteService;
    private final KanyeQuoteClient kanyeQuoteClient = KanyeQuoteClient.getInstance();

    public static KanyeQuoteService getInstance() {
        if(kanyeQuoteService == null) {
            kanyeQuoteService = new KanyeQuoteService();
        }
        return kanyeQuoteService;
    }

    public String getQuote() {
        return kanyeQuoteClient.getKanyeQuote().getQuote();
    }
}
