package com.kodilla.clothesfactory_frontend.service;

import com.kodilla.clothesfactory_frontend.configuration.client.SignInHistoryClient;
import com.kodilla.clothesfactory_frontend.domain.SignInHistory;

import java.util.List;

public class SignInHistoryService {

    private static SignInHistoryService signInHistoryService;
    private final SignInHistoryClient signInHistoryClient = SignInHistoryClient.getInstance();

    public static SignInHistoryService getInstance() {
        if(signInHistoryService == null) {
            signInHistoryService = new SignInHistoryService();
        }
        return signInHistoryService;
    }

    public List<SignInHistory> getSignInHistory() {
        return signInHistoryClient.getSignInHistory();
    }
}
