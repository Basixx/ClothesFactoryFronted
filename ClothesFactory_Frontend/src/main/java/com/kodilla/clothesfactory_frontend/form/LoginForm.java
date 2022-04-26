package com.kodilla.clothesfactory_frontend.form;

import com.kodilla.clothesfactory_frontend.views.MainView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;
import lombok.*;


public class LoginForm extends FormLayout {
    private MainView mainView;
    private AccountForm accountForm = new AccountForm(mainView);
    private TextField emailAddress = new TextField("Email Addres");
    private TextField password = new TextField("Password");
    Button login = new Button("Login", event -> showAccountForm());

    public LoginForm(MainView mainView) {
        this.mainView = mainView;
        add(emailAddress, password, login);
    }

    private void showAccountForm() {
        removeAll();
        add(accountForm);
    }
}
