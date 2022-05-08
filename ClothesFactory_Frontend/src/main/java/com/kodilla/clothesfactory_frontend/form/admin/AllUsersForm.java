package com.kodilla.clothesfactory_frontend.form.admin;

import com.kodilla.clothesfactory_frontend.domain.User;
import com.kodilla.clothesfactory_frontend.service.UserService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class AllUsersForm extends VerticalLayout {

    private final UserService userService = UserService.getInstance();
    private final Grid<User> userGrid = new Grid<>(User.class);

    public AllUsersForm() {
        userGrid.setColumns("name", "surname", "phoneNumber", "emailAddress");
        add(new Text("All Users:"));
        add(userGrid);
        userGrid.setItems(userService.getAllUsers());
    }
}
