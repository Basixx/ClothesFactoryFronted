package com.kodilla.clothesfactory_frontend.form;

import com.kodilla.clothesfactory_frontend.domain.User;
import com.kodilla.clothesfactory_frontend.service.UserService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

public class LoginForm extends FormLayout {
    private final TextField emailAddress = new TextField("Email Address");
    private final TextField password = new TextField("Password");

    private final Button previousPage = new Button("Previous Page", event -> previous());
    Button login = new Button("Login", event -> authenticateUser(emailAddress.getValue(),password.getValue()));
    UserService userService = UserService.getInstance();

    public LoginForm() {
        add(new VerticalLayout(new Text("LOGIN"), previousPage, emailAddress, password, login));
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
            Notification.show("Wrong credentials.");
        }
    }

    private void previous(){
        UI.getCurrent().getPage().reload();
    }
}
