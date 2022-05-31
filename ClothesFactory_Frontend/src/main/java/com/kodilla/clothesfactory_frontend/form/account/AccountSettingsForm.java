package com.kodilla.clothesfactory_frontend.form.account;

import com.kodilla.clothesfactory_frontend.domain.User;
import com.kodilla.clothesfactory_frontend.service.UserService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class AccountSettingsForm extends VerticalLayout {
    private final UserService userService = UserService.getInstance();
    private final Grid<User> userGrid = new Grid<>(User.class);

    public AccountSettingsForm(int userId) {
        AccountForm accountForm = new AccountForm(this, userId);
        userGrid.setColumns("name", "surname", "phoneNumber", "emailAddress", "password",
                "street", "streetAndApartmentNumber", "city", "postCode");
        refreshUser(userId);
        add(new Text("Account Settings"), userGrid, accountForm);
        userGrid.asSingleSelect().addValueChangeListener(event -> accountForm.setUser(userGrid.asSingleSelect().getValue()));
    }

    public void refreshUser(int userID) {
        userGrid.setItems(userService.getUser(userID));
        userGrid.getColumns().forEach(column -> column.setAutoWidth(true));
    }
}