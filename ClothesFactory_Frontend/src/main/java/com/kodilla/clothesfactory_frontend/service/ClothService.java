package com.kodilla.clothesfactory_frontend.service;

import com.kodilla.clothesfactory_frontend.domain.Cloth;
import com.kodilla.clothesfactory_frontend.form.auxiliary.ClothColor;
import com.kodilla.clothesfactory_frontend.form.auxiliary.ClothFashion;
import com.kodilla.clothesfactory_frontend.form.auxiliary.ClothFont;
import com.kodilla.clothesfactory_frontend.form.auxiliary.ClothSize;
import java.util.ArrayList;
import java.util.List;

public class ClothService {
    private List<Cloth> clothes;

    private static ClothService clothService;

    private ClothService(List<Cloth> clothes) {
//        this.clothes = clothes;
        this.clothes = exampleData();
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

    private List<Cloth> exampleData(){
        List<Cloth> clothes = new ArrayList<>();
        clothes.add(new Cloth(
                ClothFashion.T_SHIRT, ClothColor.RED, "hello", ClothFont.COMIC_SANS, ClothColor.WHITE, ClothSize.L, 3));
        return clothes;
    }

    public void save(Cloth cloth) {
        this.clothes.add(cloth);
    }

    public void delete(Cloth cloth) {
        this.clothes.remove(cloth);
    }
}
