package com.kodilla.clothesfactory_frontend.service;

import com.kodilla.clothesfactory_frontend.configuration.client.ClothesClient;
import com.kodilla.clothesfactory_frontend.domain.Cloth;
import com.kodilla.clothesfactory_frontend.form.auxiliary.ClothColor;
import com.kodilla.clothesfactory_frontend.form.auxiliary.ClothFashion;
import com.kodilla.clothesfactory_frontend.form.auxiliary.ClothFont;
import com.kodilla.clothesfactory_frontend.form.auxiliary.ClothSize;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ClothService {
    private List<Cloth> clothes;

    private static ClothService clothService;
    private ClothesClient clothesClient = ClothesClient.getInstance();

    private ClothService(List<Cloth> clothes) {
        this.clothes = clothes;
//        this.clothes = exampleData();
//        this.clothes = fromBackend();
    }

    public static ClothService getInstance() {
        if (clothService == null) {
            clothService = new ClothService(new ArrayList<Cloth>());
        }
        return clothService;
    }

    public List<Cloth> getClothes() {
        return new ArrayList<>(clothes);
    }

    private List<Cloth> exampleData() {
        List<Cloth> clothes = new ArrayList<>();
        clothes.add(new Cloth(
                1L, ClothFashion.T_SHIRT, ClothColor.RED, "hello", ClothFont.COMIC_SANS, ClothColor.WHITE, ClothSize.L, 3, new BigDecimal(50)));
        return clothes;
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

    public void save(Cloth cloth) {
        clothesClient.createCloth(cloth);
    }
    public void update(int clothId, Cloth cloth) {
        clothesClient.updateCloth(clothId, cloth);
    }

    public void delete(Cloth cloth) {
        this.clothes.remove(cloth);
    }
}
