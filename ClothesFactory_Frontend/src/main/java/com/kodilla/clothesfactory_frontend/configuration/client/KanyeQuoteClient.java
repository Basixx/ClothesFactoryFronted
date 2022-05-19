package com.kodilla.clothesfactory_frontend.configuration.client;

import com.kodilla.clothesfactory_frontend.domain.KanyeQuote;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
public class KanyeQuoteClient {

    private final RestTemplate restTemplate;
    private static KanyeQuoteClient kanyeQuoteClient;
    private static final Logger LOGGER = LoggerFactory.getLogger(KanyeQuoteClient.class);
    private final String url = "http://localhost:8080/v1/kanye";

    public static KanyeQuoteClient getInstance() {
        if(kanyeQuoteClient == null) {
            kanyeQuoteClient = new KanyeQuoteClient(new RestTemplate());
        }
        return kanyeQuoteClient;
    }

    public KanyeQuote getKanyeQuote() {
        try{
            KanyeQuote kanyeQuoteResponse = restTemplate.getForObject(
                    url,
                    KanyeQuote.class
            );
            return kanyeQuoteResponse;
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }
}
