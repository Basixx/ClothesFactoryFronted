package com.kodilla.clothesfactory_frontend.form.account;

import com.kodilla.clothesfactory_frontend.domain.User;
import com.kodilla.clothesfactory_frontend.service.UserService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import org.springframework.web.client.RestClientException;

public class LoginForm extends FormLayout {
    private final TextField emailAddress = new TextField("Email Address");
    private final TextField password = new TextField("Password");

    private final Button previousPage = new Button("Previous Page", event -> previous());
    private final Button login = new Button("Login", event -> authenticateUser(emailAddress.getValue(),password.getValue()));
    private final UserService userService = UserService.getInstance();

    public LoginForm() {
        add(new VerticalLayout(new Text("LOGIN"), previousPage, emailAddress, password, login));
    }

    private void showAccountForm(int userId) {
        removeAll();
        add(new AccountForm(userId));
    }

    private void authenticateUser(String email, String password) {
        try {
            User existingUser = userService.authenticateUser(email, password);
            showAccountForm(existingUser.getId().intValue());
        } catch (RestClientException e) {
            Notification.show(e.getMessage());
        }
    }

    private void previous(){
        UI.getCurrent().getPage().reload();
    }
}