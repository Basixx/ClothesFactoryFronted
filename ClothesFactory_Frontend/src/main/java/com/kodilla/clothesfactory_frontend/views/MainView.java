package com.kodilla.clothesfactory_frontend.views;

import com.kodilla.clothesfactory_frontend.form.admin.AdminForm;
import com.kodilla.clothesfactory_frontend.form.account.LoginForm;
import com.kodilla.clothesfactory_frontend.form.account.CreateUserForm;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route
public class MainView  extends VerticalLayout {
    private final Button login = new Button("Login", event -> showLoginForm());
    private final Button signIn = new Button("Sign in", event -> showSignInForm());
    private final Button admin = new Button("Admin", event -> showAdminForm());
    private final LoginForm loginForm = new LoginForm();
    private final CreateUserForm createUserForm = new CreateUserForm();
    private final AdminForm adminForm = new AdminForm();
    private final HorizontalLayout menu = new HorizontalLayout(login, signIn, admin);

    public MainView() {
        prepareView();
        add(menu);
    }

    private void showSignInForm() {
        prepareView();
        add(createUserForm);

    }

    private void showLoginForm() {
        prepareView();
        add(loginForm);
    }

    private void showAdminForm() {
        prepareView();
        add(adminForm);
    }

    private void prepareView() {
        removeAll();
        add(new Text("C L O T H E S   F A C T O R Y"));
    }
}