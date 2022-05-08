package com.kodilla.clothesfactory_frontend.service;

import com.kodilla.clothesfactory_frontend.configuration.client.ClothesClient;
import com.kodilla.clothesfactory_frontend.domain.Cloth;
import org.springframework.web.client.RestClientException;

import java.util.List;

public class ClothService {

    private static ClothService clothService;
    private final ClothesClient clothesClient = ClothesClient.getInstance();

    public static ClothService getInstance() {
        if (clothService == null) {
            clothService = new ClothService();
        }
        return clothService;
    }

    public List<Cloth> getAllClothes() {
        return clothesClient.getAllClothes();
    }

    public List<Cloth> getClothesFromUserCart(int userId) {
        return clothesClient.getClothesFromUserCart(userId);
    }

    public List<Cloth> getClothesFromOrder(int orderId) {
        return clothesClient.getClothesFromOrder(orderId);
    }

    public Cloth createCloth(Cloth cloth) {
        try {
            return clothesClient.createCloth(cloth);
        } catch (RestClientException e) {
            throw e;
        }
    }
    public void updateCloth(int clothId, Cloth cloth) { //todo
        try {
            clothesClient.updateCloth(clothId, cloth);
        } catch (RestClientException e) {
            throw e;
        }
    }
}