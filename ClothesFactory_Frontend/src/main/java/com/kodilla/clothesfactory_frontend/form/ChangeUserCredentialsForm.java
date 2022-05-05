package com.kodilla.clothesfactory_frontend.form;

import com.kodilla.clothesfactory_frontend.domain.User;
import com.kodilla.clothesfactory_frontend.service.UserService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class ChangeUserCredentialsForm extends VerticalLayout {
    private final AccountForm accountForm;
    private final TextField name = new TextField("Name");
    private final TextField surname = new TextField("Surname");
    private final TextField phoneNumber = new TextField("Phone Number");
    private final TextField emailAddress = new TextField("Email Address");
    private final TextField password = new TextField("Password");
    private final UserService userService = UserService.getInstance();
    private final Binder<User> binder = new Binder<>(User.class);
    private final Button updateAccount = new Button("Update Credentials");

    private final Button deleteAccount = new Button("Delete Account", event -> delete());

    public ChangeUserCredentialsForm(AccountForm accountForm) {
        this.accountForm = accountForm;
        add(name, surname, phoneNumber, emailAddress, password, updateAccount, deleteAccount);
        binder.bindInstanceFields(this);
        updateAccount.addClickListener(event -> save(accountForm.getUserId()));

    }

    private void save(int userId) {
        User user = binder.getBean();
        userService.updateUser(userId, user);
        accountForm.refreshUser(accountForm.getUserId());
        setUser(null);
    }

    private void delete() {
        User user = binder.getBean();
        userService.deleteUser(user.getId().intValue());
        setUser(null);
        UI.getCurrent().getPage().reload();
    }
    public void setUser(User user) {
        binder.setBean(user);
        setVisible(user != null);
    }
}
