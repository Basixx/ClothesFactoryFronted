package com.kodilla.clothesfactory_frontend.form;

import com.kodilla.clothesfactory_frontend.MainView;
import com.kodilla.clothesfactory_frontend.domain.Cloth;
import com.kodilla.clothesfactory_frontend.form.auxiliary.*;
import com.kodilla.clothesfactory_frontend.service.ClothService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

import java.math.BigDecimal;
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
    private Button save = new Button("Save");
    private Button delete = new Button("Delete");
    private Binder<Cloth> binder = new Binder<>(Cloth.class);
    private MainView mainView;
    private ClothService service = ClothService.getInstance();
    private void setQuantityComboBox () {
        List<Integer> optionsList = new ArrayList<>();
        for (int i = 1; i <= 10; i++){
            optionsList.add(i);
        }
        quantity.setItems(optionsList);
    }


    public ClothForm(MainView mainView) {
        this.mainView = mainView;
        fashion.setItems(ClothFashion.values());
        color.setItems(ClothColor.values());
        font.setItems(ClothFont.values());
        printColor.setItems(ClothColor.values());
        size.setItems(ClothSize.values());
        setQuantityComboBox();
        HorizontalLayout buttons = new HorizontalLayout(save, delete);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(fashion, color, print, font, printColor, size, quantity, buttons);
        binder.bindInstanceFields(this);
        save.addClickListener(event -> save());
        delete.addClickListener(event -> delete());

    }

    private void save() {
        Cloth cloth = binder.getBean();
        //dołączyć metodę liczenia ceny
        //cloth.setPrice(new BigDecimal(50));
        service.save(cloth);
        mainView.refreshClothes();
        setCloth(null);
    }

    private void delete() {
        Cloth cloth = binder.getBean();
        service.delete(cloth);
        mainView.refreshClothes();
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
