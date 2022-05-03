package com.kodilla.clothesfactory_frontend.form;


import com.kodilla.clothesfactory_frontend.domain.Cloth;
import com.kodilla.clothesfactory_frontend.form.auxiliary.*;
import com.kodilla.clothesfactory_frontend.service.CartService;
import com.kodilla.clothesfactory_frontend.service.ClothService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

import java.util.ArrayList;
import java.util.List;

public class ClothForm extends FormLayout {
    private ComboBox<ClothFashion> fashion = new ComboBox<>("Fashion");
    private ComboBox<ClothColor> color = new ComboBox<>("Color");
    private TextField print = new TextField("Print");
    private ComboBox<ClothFont> font = new ComboBox<>("Font");
    private ComboBox<ClothColor> printColor = new ComboBox<>("Print Color");
    private ComboBox<ClothSize> size = new ComboBox<>("Size");
    private ComboBox<Integer> quantity = new ComboBox<>("Quantity");
    public Button add = new Button("Add");
    public Button delete = new Button("Delete");
    public Button save = new Button("SAVE");
    private Binder<Cloth> binder = new Binder<>(Cloth.class);
    private AccountForm accountForm;
    private ClothService clothService = ClothService.getInstance();
    private CartService cartService = CartService.getInstance();

    public HorizontalLayout buttons = new HorizontalLayout(add, delete);


    private void setQuantityComboBox () {
        List<Integer> optionsList = new ArrayList<>();
        for (int i = 1; i <= 10; i++){
            optionsList.add(i);
        }
        quantity.setItems(optionsList);
    }


    public ClothForm(AccountForm accountForm) {
        this.accountForm = accountForm;
        fashion.setItems(ClothFashion.values());
        color.setItems(ClothColor.values());
        font.setItems(ClothFont.values());
        printColor.setItems(ClothColor.values());
        size.setItems(ClothSize.values());
        setQuantityComboBox();

        add.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(fashion, color, print, font, printColor, size, quantity, buttons);
        binder.bindInstanceFields(this);
        add.addClickListener(event -> save());
        delete.addClickListener(event -> delete());
        save.addClickListener(event -> update());

    }

    private void save() {
        Cloth cloth = binder.getBean();
        clothService.save(cloth);
        cartService.addClothToCart(97, 109);   //HARDCODED
        accountForm.refreshClothes();
        setCloth(null);
    }

    private void delete() {
        Cloth cloth = binder.getBean();
//        clothService.delete(cloth);
        cartService.deleteClothFromCart(97, cloth.getId().intValue());  //HARDCODED
        accountForm.refreshClothes();
        setCloth(null);
    }

    private void update() {
        Cloth cloth = binder.getBean();
        clothService.update(cloth.getId().intValue(), cloth);
        accountForm.refreshClothes();
        setCloth(null);
    }

    public void setCloth(Cloth cloth) {
        binder.setBean(cloth);

        if (cloth == null) {
            setVisible(false);
        } else {
            setVisible(true);
            fashion.focus();
        }
    }



}
