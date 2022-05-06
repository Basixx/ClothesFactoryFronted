package com.kodilla.clothesfactory_frontend.form;

import com.kodilla.clothesfactory_frontend.domain.Cart;
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
    private final ComboBox<ClothFashion> fashion = new ComboBox<>("Fashion");
    private final ComboBox<ClothColor> color = new ComboBox<>("Color");
    private final TextField print = new TextField("Print");
    private final ComboBox<ClothFont> font = new ComboBox<>("Font");
    private final ComboBox<ClothColor> printColor = new ComboBox<>("Print Color");
    private final ComboBox<ClothSize> size = new ComboBox<>("Size");
    private final ComboBox<Integer> quantity = new ComboBox<>("Quantity");
    public Button add = new Button("Add");
    public Button delete = new Button("Delete");
    public Button save = new Button("SAVE");
    private final Binder<Cloth> clothBinder = new Binder<>(Cloth.class);
    private final AccountForm accountForm;
    private final ClothService clothService = ClothService.getInstance();
    private final CartService cartService = CartService.getInstance();
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
        clothBinder.bindInstanceFields(this);
        add.addClickListener(event -> save(accountForm.getUserId()));
        delete.addClickListener(event -> delete(accountForm.getUserId()));
        save.addClickListener(event -> update(accountForm.getUserId()));

    }

    private void save(int userId) {
        Cloth cloth = clothBinder.getBean();
        Cloth createdCloth = clothService.createCloth(cloth);

        Cart cart = cartService.getCartFromUser(userId);
        int usersCartId = cart.getId().intValue();
        cartService.addClothToCart(usersCartId, createdCloth.getId().intValue());
        accountForm.refreshClothes(userId);
        setCloth(null);
    }

    private void delete(int userId) {
        Cloth cloth = clothBinder.getBean();
        Cart cart = cartService.getCartFromUser(userId);
        int usersCartId = cart.getId().intValue();
        cartService.deleteClothFromCart(usersCartId, cloth.getId().intValue());
        accountForm.refreshClothes(userId);
        setCloth(null);
    }

    private void update(int userId) {
        Cloth cloth = clothBinder.getBean();
        clothService.updateCloth(cloth.getId().intValue(), cloth);
        accountForm.refreshClothes(userId);
        setCloth(null);
    }

    public void setCloth(Cloth cloth) {
        clothBinder.setBean(cloth);

        if (cloth == null) {
            setVisible(false);
        } else {
            setVisible(true);
            fashion.focus();
        }
    }
}
