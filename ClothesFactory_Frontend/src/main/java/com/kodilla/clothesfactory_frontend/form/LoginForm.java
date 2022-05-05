package com.kodilla.clothesfactory_frontend.form;

import com.kodilla.clothesfactory_frontend.domain.User;
import com.kodilla.clothesfactory_frontend.service.UserService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;

public class LoginForm extends FormLayout {
    private final TextField emailAddress = new TextField("Email Address");
    private final TextField password = new TextField("Password");
    private final Text wrongCredentials = new Text("");
    Button login = new Button("Login", event -> authenticateUser(emailAddress.getValue(),password.getValue()));
    UserService userService = UserService.getInstance();

    public LoginForm() {
        add(emailAddress, password, login);
        add(wrongCredentials);
    }

    private void showAccountForm(int userId) {
        removeAll();
        add(new AccountForm(userId));
    }

    private void authenticateUser(String email, String password) {
        User existingUser = userService.authenticateUser(email, password);
        if(existingUser != null){
            showAccountForm(existingUser.getId().intValue());
        } else {
            wrongCredentials.setText("Wrong username or password");
        }
    }
}
