package com.kodilla.clothesfactory_frontend.form.account;

import com.kodilla.clothesfactory_frontend.domain.Cart;
import com.kodilla.clothesfactory_frontend.domain.Cloth;
import com.kodilla.clothesfactory_frontend.form.auxiliary.*;
import com.kodilla.clothesfactory_frontend.service.CartService;
import com.kodilla.clothesfactory_frontend.service.ClothService;
import com.kodilla.clothesfactory_frontend.service.KanyeQuoteService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import org.springframework.web.client.RestClientException;
import java.util.ArrayList;
import java.util.List;

public class ClothForm extends FormLayout {
    private final CartForm cartForm;
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
    public Button kanyeQuoteButton = new Button("Get Quote", e -> showKanyeQuote());
    public Text kanyeExplain = new Text("No idea for your print? Get an inspiration from a random Kanye West quote!");
    public Text kanyeQuote = new Text("");
    private final Binder<Cloth> clothBinder = new Binder<>(Cloth.class);
    private final ClothService clothService = ClothService.getInstance();
    private final CartService cartService = CartService.getInstance();
    private final KanyeQuoteService kanyeQuoteService = KanyeQuoteService.getInstance();
    public HorizontalLayout buttons = new HorizontalLayout(add, delete);
    public HorizontalLayout kanye = new HorizontalLayout(kanyeQuoteButton, kanyeQuote);

    private void setQuantityComboBox () {
        List<Integer> optionsList = new ArrayList<>();
        for (int i = 1; i <= 10; i++){
            optionsList.add(i);
        }
        quantity.setItems(optionsList);
    }

    public ClothForm(CartForm cartForm, int userId) {
        this.cartForm = cartForm;
        fashion.setItems(ClothFashion.values());
        color.setItems(ClothColor.values());
        font.setItems(ClothFont.values());
        printColor.setItems(ClothColor.values());
        size.setItems(ClothSize.values());
        setQuantityComboBox();

        add.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(fashion, color, print, font, printColor, size, quantity, buttons, kanyeExplain, kanye);
        clothBinder.bindInstanceFields(this);
        add.addClickListener(event -> save(userId));
        delete.addClickListener(event -> delete(userId));
        save.addClickListener(event -> update(userId));
    }

    private void save(int userId) {
        if(fashion.getValue() == null || color.getValue() == null || print.getValue().equals("") || font.getValue() == null || printColor.getValue() == null || size.getValue() ==null) {
            Notification.show("Please provide all the data to create cloth.");
        } else {
            Cloth cloth = clothBinder.getBean();
            try {
                Cloth createdCloth = clothService.createCloth(cloth);
                Cart cart = cartService.getCartFromUser(userId);
                int usersCartId = cart.getId().intValue();
                cartService.addClothToCart(usersCartId, createdCloth.getId().intValue());
                cartForm.refreshClothes(userId);
                setCloth(null);
                Notification.show("Cloth has been added to cart!");
            } catch (RestClientException e) {
                Notification.show(e.getMessage());
            }
        }
    }

    private void delete(int userId) {
        Cloth cloth = clothBinder.getBean();
        Cart cart = cartService.getCartFromUser(userId);
        int usersCartId = cart.getId().intValue();
        cartService.deleteClothFromCart(usersCartId, cloth.getId().intValue());
        cartForm.refreshClothes(userId);
        setCloth(null);
    }

    private void update(int userId) {
        Cloth cloth = clothBinder.getBean();
        try {
            clothService.updateCloth(cloth.getId().intValue(), cloth);
        } catch (RestClientException e) {
            Notification.show(e.getMessage());
        } finally {
            cartForm.refreshClothes(userId);
            setCloth(null);
        }
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

    private void showKanyeQuote() {
        String quote = kanyeQuoteService.getQuote();
        kanyeQuote.setText(quote);
    }
}