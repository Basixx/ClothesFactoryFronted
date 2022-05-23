package com.kodilla.clothesfactory_frontend.form.account;

import com.kodilla.clothesfactory_frontend.domain.Cloth;
import com.kodilla.clothesfactory_frontend.form.auxiliary.OrderShipment;
import com.kodilla.clothesfactory_frontend.service.CartService;
import com.kodilla.clothesfactory_frontend.service.ClothService;
import com.kodilla.clothesfactory_frontend.service.OrderService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.springframework.web.client.RestClientException;
import java.math.BigDecimal;

public class CartForm extends VerticalLayout {
    private final CartService cartService = CartService.getInstance();
    private final OrderService orderService = OrderService.getInstance();
    private final ClothService clothService = ClothService.getInstance();
    private final Grid<Cloth> clothGrid = new Grid<>(Cloth.class);
    private final Button addNewCloth = new Button("Add new cloth");
    private final Button createOrder = new Button("Create Order");
    private final ComboBox<OrderShipment> shipmentComboBox = new ComboBox<>("Shipment");
    private BigDecimal totalPrice;
    private final Text price;
    private final CurrencyForm currencyForm = new CurrencyForm(this);

    public CartForm(int userId) {
        ClothForm clothForm = new ClothForm(this, userId);

        totalPrice = (cartService.getCartFromUser(userId).getTotalPrice() == null ? BigDecimal.ZERO : cartService.getCartFromUser(userId).getTotalPrice());
        price = new Text("");

        shipmentComboBox.setItems(OrderShipment.values());
        shipmentComboBox.setValue(OrderShipment.FEDEX);

        clothForm.setCloth(null);
        clothGrid.setColumns("fashion", "color", "print", "font", "printColor", "size", "quantity", "price");
        addNewCloth.addClickListener(e -> {
            clothGrid.asSingleSelect().clear();
            clothForm.setCloth(new Cloth());
            clothForm.buttons.remove(clothForm.save);
            clothForm.buttons.add(clothForm.add);
        });

        add(new Text("My Cart"));
        add(addNewCloth);

        createOrder.addClickListener(e -> createOrder(userId, shipmentComboBox.getValue()));

        VerticalLayout clothLayout = new VerticalLayout(clothGrid, price);
        add(clothLayout);
        add(clothForm);
        setSizeFull();
        refreshClothes(userId);
        clothGrid.asSingleSelect().addValueChangeListener(event -> {
            clothForm.setCloth(clothGrid.asSingleSelect().getValue());
            clothForm.buttons.remove(clothForm.add);
            clothForm.buttons.add(clothForm.save);
        });
        add(currencyForm);
        add(shipmentComboBox);
        add(new Text("Fedex: 10 PLN / / DHL: 15 PLN / / UPS: 20 PLN / / InPost: 12 PLN"));
        add(createOrder);
    }

    private void createOrder(int userID, OrderShipment orderShipment) {
        try {
            orderService.createOrder(userID, orderShipment);
            shipmentComboBox.getGenericDataView();
        } catch (RestClientException e) {
            Notification.show(e.getMessage());
        } finally {
            refreshClothes(userID);
        }
    }

    public void refreshClothes(int userID) {
        clothGrid.setItems(clothService.getClothesFromUserCart(userID));
        totalPrice = cartService.getCartFromUser(userID).getTotalPrice();
        price.setText("Total price: " + totalPrice + " PLN");
        currencyForm.clearCurrency();
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }
}