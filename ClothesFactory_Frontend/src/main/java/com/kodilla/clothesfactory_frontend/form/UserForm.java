package com.kodilla.clothesfactory_frontend.form;

import com.kodilla.clothesfactory_frontend.domain.Cloth;
import com.kodilla.clothesfactory_frontend.domain.User;
import com.kodilla.clothesfactory_frontend.service.UserService;
import com.kodilla.clothesfactory_frontend.views.MainView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class UserForm extends FormLayout {
    private MainView mainView;
    private TextField name = new TextField("Name");
    private TextField surname = new TextField("Surname");
    private TextField phoneNumber = new TextField("Phone Number");
    private TextField emailAddress = new TextField("Email Addres");
    private TextField password = new TextField("Password");
    private UserService userService = UserService.getInstance();
    private Binder<User> binder = new Binder<>(User.class);
    private Button createAccount = new Button(
            "Create Account", event -> {
                showAccountForm();
            });
    private AccountForm accountForm = new AccountForm(mainView);


    public UserForm(MainView mainView) {
        this.mainView = mainView;
        add(name, surname, phoneNumber, emailAddress, password, createAccount);

    }

    private void save() {
        User user = binder.getBean();
        userService.save(user);
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

    private void showAccountForm() {
        removeAll();
        add(accountForm);
    }
}
