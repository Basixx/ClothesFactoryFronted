package com.kodilla.clothesfactory_frontend.form.admin;

import com.kodilla.clothesfactory_frontend.domain.Cloth;
import com.kodilla.clothesfactory_frontend.service.ClothService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class AllClothesForm extends VerticalLayout {

    private final ClothService clothService = ClothService.getInstance();
    private final Grid<Cloth> clothGrid = new Grid<>(Cloth.class);

    public AllClothesForm() {
        clothGrid.setColumns("fashion", "color", "print", "font", "printColor", "size", "quantity", "price");
        add(new Text("All Clothes:"));
        add(clothGrid);
        clothGrid.setItems(clothService.getAllClothes());
    }
}