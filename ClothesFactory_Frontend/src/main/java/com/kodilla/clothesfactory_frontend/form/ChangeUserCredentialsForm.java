package com.kodilla.clothesfactory_frontend.form;

import com.kodilla.clothesfactory_frontend.domain.Cloth;
import com.kodilla.clothesfactory_frontend.domain.User;
import com.kodilla.clothesfactory_frontend.service.UserService;
import com.kodilla.clothesfactory_frontend.views.MainView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import jdk.tools.jmod.Main;

import javax.swing.*;

public class ChangeUserCredentialsForm extends FormLayout {
    private AccountForm accountForm;
    MainView mainView;
    private TextField name = new TextField("Name");
    private TextField surname = new TextField("Surname");
    private TextField phoneNumber = new TextField("Phone Number");
    private TextField emailAddress = new TextField("Email Addres");
    private TextField password = new TextField("Password");
    private UserService userService = UserService.getInstance();
    private Binder<User> binder = new Binder<>(User.class);
    private Button updateAccount = new Button("Update Credentials");

    private Button deleteAccount = new Button("Delete Account", event -> delete());

    public ChangeUserCredentialsForm(AccountForm accountForm) {
        this.accountForm = accountForm;
        add(name, surname, phoneNumber, emailAddress, password, updateAccount, deleteAccount);
        binder.bindInstanceFields(this);
    }

    private void save() {
        User user = binder.getBean();
        userService.save(user); //tu zmienić na update user
        setUser(null);
    }

    private void delete() {
        User user = binder.getBean();
        //userService.delete(user);
        setUser(null);
    }
    public void setUser(User user) {
        binder.setBean(user);

        if (user == null) {
            setVisible(false);
        } else {
            setVisible(true);
        }
    }
}
