package com.kodilla.clothesfactory_frontend.service;

import com.kodilla.clothesfactory_frontend.configuration.client.AdminTokenClient;
import com.kodilla.clothesfactory_frontend.domain.AdminToken;

public class AdminTokenService {

    private static AdminTokenService adminTokenService;
    private final AdminTokenClient adminTokenClient = AdminTokenClient.getInstance();

    public static AdminTokenService getInstance() {
        if (adminTokenService == null) {
            adminTokenService = new AdminTokenService();
        }
        return adminTokenService;
    }

    public Boolean existsToken(String token) {
        return adminTokenClient.existsToken(token);
    }

    public void createToken() {
        adminTokenClient.createToken();
    }

    public void deleteTokens() {
        adminTokenClient.deleteTokens();
    }
}
