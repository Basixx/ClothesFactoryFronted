package com.kodilla.clothesfactory_frontend.form.account;

import com.kodilla.clothesfactory_frontend.domain.User;
import com.kodilla.clothesfactory_frontend.service.UserService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.component.notification.Notification;

public class UserForm extends VerticalLayout {
    private final AccountSettingsForm accountSettingsFormForm;
    private final TextField name = new TextField("Name");
    private final TextField surname = new TextField("Surname");
    private final TextField phoneNumber = new TextField("Phone Number");
    private final TextField password = new TextField("Password");
    private final TextField street = new TextField("Street");
    private final TextField streetAndApartmentNumber = new TextField("Street and apartment number");
    private final TextField city = new TextField("City");
    private final TextField postCode = new TextField("Post code");
    private final UserService userService = UserService.getInstance();
    private final Binder<User> binder = new Binder<>(User.class);
    private final Button updateAccount = new Button("Update Credentials");

    private final Button deleteAccount = new Button("Delete Account", event -> delete());

    public UserForm(AccountSettingsForm accountSettingsForm, int id) {
        this.accountSettingsFormForm = accountSettingsForm;
        setUser(null);
        add(name, surname, phoneNumber, password, street, streetAndApartmentNumber, city, postCode, updateAccount, deleteAccount);
        binder.bindInstanceFields(this);
        updateAccount.addClickListener(event -> save(id));
    }

    private void save(int userId) {
        if(name.getValue().equals("") || surname.getValue().equals("") || phoneNumber.getValue().equals("") ||
                password.getValue().equals("") || street.getValue().equals("") ||
                streetAndApartmentNumber.getValue().equals("") || city.getValue().equals("") || postCode.getValue().equals("")) {
            Notification.show("Please provide all the data to update user credentials.");
        } else {
            User user = binder.getBean();
            userService.updateUser(userId, user);
            accountSettingsFormForm.refreshUser(userId);
            setUser(null);
        }
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