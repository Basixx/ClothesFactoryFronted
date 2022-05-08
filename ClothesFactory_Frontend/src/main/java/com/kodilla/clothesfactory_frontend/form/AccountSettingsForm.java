package com.kodilla.clothesfactory_frontend.form;

import com.kodilla.clothesfactory_frontend.domain.User;
import com.kodilla.clothesfactory_frontend.service.UserService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class AccountSettingsForm extends VerticalLayout {
    private final UserService userService = UserService.getInstance();
    private final Grid<User> userGrid = new Grid<>(User.class);

    public AccountSettingsForm(int userId) {
        UserForm userForm = new UserForm(this, userId);
        userGrid.setColumns("name", "surname", "phoneNumber", "emailAddress", "password");
        refreshUser(userId);
        add(new Text("Account Settings"), userGrid, userForm);
        userGrid.asSingleSelect().addValueChangeListener(event -> userForm.setUser(userGrid.asSingleSelect().getValue()));

    }

    public void refreshUser(int userID) {
        userGrid.setItems(userService.getUser(userID));
    }
}
