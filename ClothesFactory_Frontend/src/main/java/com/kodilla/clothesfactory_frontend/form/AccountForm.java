package com.kodilla.clothesfactory_frontend.form;

import com.kodilla.clothesfactory_frontend.domain.Cloth;
import com.kodilla.clothesfactory_frontend.domain.Order;
import com.kodilla.clothesfactory_frontend.domain.User;
import com.kodilla.clothesfactory_frontend.service.ClothService;
import com.kodilla.clothesfactory_frontend.service.OrderService;
import com.kodilla.clothesfactory_frontend.service.UserService;
import com.kodilla.clothesfactory_frontend.views.MainView;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class AccountForm extends FormLayout {
    private MainView mainView;
    private ClothService clothService = ClothService.getInstance();
    private OrderService orderService = OrderService.getInstance();
    private UserService userService = UserService.getInstance();

    Button createNewOrder = new Button("Create New Order",  event -> showClothes());
    Button myOrders = new Button("My Orders", event -> showOrders());
    Button accountSettings = new Button("Account Settings", event -> showAccountSettings());
    private Button addNewCloth = new Button("Add new cloth");
    private Button createOrder = new Button("Create Order");
    private Grid<Cloth> clothGrid = new Grid<>(Cloth.class);
    private Grid<Order> orderGrid = new Grid<>(Order.class);
    private Grid<User> userGrid = new Grid<>(User.class);
    private ClothForm clothForm = new ClothForm(this);
    private ChangeUserCredentialsForm changeUserCredentialsForm = new ChangeUserCredentialsForm(this);

    HorizontalLayout toolbar = new HorizontalLayout(createNewOrder, myOrders, accountSettings);
    VerticalLayout view = new VerticalLayout(new Text("ACCOUNT"), toolbar);

    public AccountForm(MainView mainView) {
        this.mainView = mainView;
        add(view);
    }

    private void showAccountView() {
        removeAll();
        add(view);
    }

    private void showClothes() {
        clothForm.setCloth(null);
        showAccountView();

        clothGrid.setColumns("fashion", "color", "print", "font", "printColor", "size", "quantity", "price");
        addNewCloth.addClickListener(e -> {
            clothGrid.asSingleSelect().clear();
            clothForm.setCloth(new Cloth());
        });
        add(addNewCloth);
        VerticalLayout clothLayout = new VerticalLayout(clothGrid, clothForm);
        add(clothLayout);
        setSizeFull();
        refreshClothes();
        clothGrid.asSingleSelect().addValueChangeListener(event -> clothForm.setCloth(clothGrid.asSingleSelect().getValue()));
        add(createOrder);
    }

    private void showOrders() {
        showAccountView();
        orderGrid.setColumns("orderDate", "totalOrderPrice");
        add(orderGrid);
        refreshOrders();
        orderGrid.asSingleSelect().addValueChangeListener(event -> {
            clothGrid.setItems(clothService.getClothes());  //tutaj dać by ORDER
            add(clothGrid);
        });
    }

    private void showAccountSettings() {
        changeUserCredentialsForm.setUser(null);
        showAccountView();
        userGrid.setColumns("name", "surname", "phoneNumber", "emailAddress", "password");
        VerticalLayout accountLayout = new VerticalLayout(userGrid, changeUserCredentialsForm);
        add(accountLayout);
        setSizeFull();;
        refreshUser();
        userGrid.asSingleSelect().addValueChangeListener(event -> changeUserCredentialsForm.setUser(userGrid.asSingleSelect().getValue()));
    }

    public void refreshClothes() {
        clothGrid.setItems(clothService.getClothes());
    }
    public void refreshOrders() {
        orderGrid.setItems(orderService.getOrders());
    }
    public void refreshUser() {
        userGrid.setItems(userService.getUsers());  //tu user by ID
    }
}
