package com.kodilla.clothesfactory_frontend.service;

import com.kodilla.clothesfactory_frontend.configuration.client.LoginHistoryClient;
import com.kodilla.clothesfactory_frontend.domain.LoginHistory;
import java.util.List;

public class LoginHistoryService {

    private static LoginHistoryService loginHistoryService;
    private final LoginHistoryClient loginHistoryClient = LoginHistoryClient.getInstance();

    public static LoginHistoryService getInstance() {
        if(loginHistoryService == null) {
            loginHistoryService = new LoginHistoryService();
        }
        return loginHistoryService;
    }

    public List<LoginHistory> getLoginHistory() {
        return loginHistoryClient.getLoginHistory();
    }
}
