package com.kodilla.clothesfactory_frontend.form;

import com.kodilla.clothesfactory_frontend.domain.Cloth;
import com.kodilla.clothesfactory_frontend.domain.Order;
import com.kodilla.clothesfactory_frontend.domain.User;
import com.kodilla.clothesfactory_frontend.service.CartService;
import com.kodilla.clothesfactory_frontend.service.ClothService;
import com.kodilla.clothesfactory_frontend.service.OrderService;
import com.kodilla.clothesfactory_frontend.service.UserService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.springframework.web.client.RestClientException;
import java.math.BigDecimal;

public class AccountForm extends VerticalLayout {
    private final int userId;
    private BigDecimal totalPrice;
    private Text price;
    private final ClothService clothService = ClothService.getInstance();
    private final OrderService orderService = OrderService.getInstance();
    private final UserService userService = UserService.getInstance();
    private final CartService cartService = CartService.getInstance();
    private final Button createNewOrder = new Button("Create New Order",  event -> showCart());
    private final Button myOrders = new Button("My Orders", event -> showOrders());
    private final Button accountSettings = new Button("Account Settings", event -> showAccountSettings());
    private final Button logOut = new Button("Log Out", event -> logout());
    private final Button addNewCloth = new Button("Add new cloth");
    private final Button createOrder = new Button("Create Order");
    private final Grid<Cloth> clothGrid = new Grid<>(Cloth.class);
    private final Grid<Order> orderGrid = new Grid<>(Order.class);
    private final Grid<User> userGrid = new Grid<>(User.class);
    private final ClothForm clothForm = new ClothForm(this);
    private final ChangeUserCredentialsForm changeUserCredentialsForm = new ChangeUserCredentialsForm(this);
    HorizontalLayout toolbar = new HorizontalLayout(createNewOrder, myOrders, accountSettings, logOut);
    VerticalLayout view = new VerticalLayout(new Text("ACCOUNT"), toolbar);

    public AccountForm(int userId) {
        this.userId = userId;
        add(view);
    }

    private void showAccountView() {
        removeAll();
        add(view);
    }

    private void showCart() {
        totalPrice = (cartService.getCartFromUser(userId).getTotalPrice() == null ? BigDecimal.ZERO : cartService.getCartFromUser(userId).getTotalPrice());
        price = new Text("Total price: " + totalPrice);
        clothForm.setCloth(null);
        showAccountView();

        clothGrid.setColumns("fashion", "color", "print", "font", "printColor", "size", "quantity", "price");
        addNewCloth.addClickListener(e -> {
            clothGrid.asSingleSelect().clear();
            clothForm.setCloth(new Cloth());
            clothForm.buttons.remove(clothForm.save);
            clothForm.buttons.add(clothForm.add);
        });
        add(new Text("My Cart"));
        add(addNewCloth);

        createOrder.addClickListener(e -> createOrder(userId));

        VerticalLayout clothLayout = new VerticalLayout(clothGrid, price, clothForm);
        add(clothLayout);
        setSizeFull();
        refreshClothes(userId);
        clothGrid.asSingleSelect().addValueChangeListener(event -> {
            clothForm.setCloth(clothGrid.asSingleSelect().getValue());
            clothForm.buttons.remove(clothForm.add);
            clothForm.buttons.add(clothForm.save);
        });
        add(createOrder);
    }

    private void showOrders() {
        showAccountView();
        orderGrid.setColumns("orderDate", "totalOrderPrice", "paid", "sent");
        add(new Text("My Orders"));
        add(orderGrid);
        refreshOrders(userId);

        orderGrid.asSingleSelect().addValueChangeListener(event -> {

            clothGrid.setItems(clothService.getClothesFromOrder(orderGrid.asSingleSelect().getValue().getId().intValue()));
            add(clothGrid);
        });
    }

    private void showAccountSettings() {
        changeUserCredentialsForm.setUser(null);
        showAccountView();
        userGrid.setColumns("name", "surname", "phoneNumber", "emailAddress", "password");
        VerticalLayout accountLayout = new VerticalLayout(new Text("Account Settings"), userGrid, changeUserCredentialsForm);
        add(accountLayout);
        refreshUser(userId);
        userGrid.asSingleSelect().addValueChangeListener(event -> changeUserCredentialsForm.setUser(userGrid.asSingleSelect().getValue()));
    }

    public void refreshClothes(int userID) {
        clothGrid.setItems(clothService.getClothesFromUserCart(userID));
        totalPrice = cartService.getCartFromUser(userID).getTotalPrice();
        price.setText("Total price: " + totalPrice);
    }
    public void refreshOrders(int userID) {
        orderGrid.setItems(orderService.getOrdersByUser(userID));
    }
    public void refreshUser(int userID) {
        userGrid.setItems(userService.getUser(userID));
    }

    private void createOrder(int userID) {
        try {
            orderService.createOrder(userID);
        } catch (RestClientException e) {
            Notification.show(e.getMessage());
        } finally {
            refreshClothes(userID);
        }
    }

    public int getUserId() {
        return userId;
    }

    private void logout(){
        UI.getCurrent().getPage().reload();
    }
}
