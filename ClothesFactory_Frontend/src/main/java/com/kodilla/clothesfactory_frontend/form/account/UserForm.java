package com.kodilla.clothesfactory_frontend.form.account;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class UserForm extends VerticalLayout {
    private final int userId;
    private final Button createNewOrder = new Button("Create New Order",  event -> showCart());
    private final Button myOrders = new Button("My Orders", event -> showOrders());
    private final Button accountSettings = new Button("Account Settings", event -> showAccountSettings());
    private final Button logOut = new Button("Log Out", event -> logout());
    HorizontalLayout toolbar = new HorizontalLayout(createNewOrder, myOrders, accountSettings, logOut);
    VerticalLayout view = new VerticalLayout(new Text("ACCOUNT"), toolbar);

    public UserForm(int userId) {
        this.userId = userId;
        add(view);
    }

    private void showAccountView() {
        removeAll();
        add(view);
    }

    private void showCart() {
        showAccountView();
        add(new CartForm(userId));
    }

    private void showOrders() {
        showAccountView();
        add(new OrdersForm(userId));
    }

    private void showAccountSettings() {
        showAccountView();
        add(new AccountSettingsForm(userId));
        setSizeFull();
    }

    private void logout(){
        UI.getCurrent().getPage().reload();
    }
}