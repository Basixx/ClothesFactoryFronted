package com.kodilla.clothesfactory_frontend.form;

import com.kodilla.clothesfactory_frontend.domain.Cloth;
import com.kodilla.clothesfactory_frontend.service.ClothService;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;

public class ClothFromOrderForm extends FormLayout {
    private ClothService clothService = ClothService.getInstance();
    private Grid<Cloth> clothGrid = new Grid<>(Cloth.class);



}
