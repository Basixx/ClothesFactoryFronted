package com.kodilla.clothesfactory_frontend.form.account;

import com.kodilla.clothesfactory_frontend.domain.User;
import com.kodilla.clothesfactory_frontend.service.UserService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import org.springframework.web.client.RestClientException;

public class CreateUserForm extends VerticalLayout {
    private final TextField name = new TextField("Name");
    private final TextField surname = new TextField("Surname");
    private final TextField phoneNumber = new TextField("Phone Number");
    private final TextField emailAddress = new TextField("Email Addres");
    private final TextField password = new TextField("Password");
    private final TextField street = new TextField("Street");
    private final TextField streetAndApartmentNumber = new TextField("Street and apartment number");
    private final TextField city = new TextField("City");
    private final TextField postCode = new TextField("Post code");
    private final UserService userService = UserService.getInstance();
    private final Button previousPage = new Button("Previous Page", event -> previous());

    public CreateUserForm() {
        Button createAccount = new Button("Create Account");
        createAccount.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        createAccount.addClickListener(event -> saveUser());

        add(new VerticalLayout(new Text("Create an account"), previousPage,
                name, surname, phoneNumber, emailAddress, password, street, streetAndApartmentNumber, city, postCode, createAccount));
    }

    private void saveUser() {
        if(name.getValue().equals("") || surname.getValue().equals("") || phoneNumber.getValue().equals("") || emailAddress.getValue().equals("") ||
                password.getValue().equals("") || street.getValue().equals("") || streetAndApartmentNumber.getValue().equals("") || city.getValue().equals("") || postCode.getValue().equals("")) {
            Notification.show("Please provide all the data to create an account");
        } else {
            User user = new User(name.getValue(), surname.getValue(), phoneNumber.getValue(), emailAddress.getValue(),
                    password.getValue(), street.getValue(), streetAndApartmentNumber.getValue(), city.getValue(), postCode.getValue());
            try {
                User createdUser = userService.createUser(user);
                showAccountForm(createdUser.getId().intValue());
            } catch (RestClientException e) {
                Notification.show(e.getMessage());
            }
        }
    }

    private void showAccountForm(int userId) {
        removeAll();
        add(new AccountForm(userId));
    }

    private void previous(){
        UI.getCurrent().getPage().reload();
    }
}