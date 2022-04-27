package com.kodilla.clothesfactory_frontend.views;

import com.kodilla.clothesfactory_frontend.form.AdminForm;
import com.kodilla.clothesfactory_frontend.form.LoginForm;
import com.kodilla.clothesfactory_frontend.form.CreateUserForm;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route
public class MainView  extends VerticalLayout {
    private Button login = new Button("Login", event -> showLoginForm());
    private Button signIn = new Button("Sign in", event -> showSignInForm());
    private Button admin = new Button("Admin", event -> showAdminForm());

    private LoginForm loginForm = new LoginForm(this);
    private CreateUserForm createUserForm = new CreateUserForm(this);
    private AdminForm adminForm = new AdminForm(this);
    HorizontalLayout menu = new HorizontalLayout(login, signIn, admin);

    public MainView() {
        prepareView();
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
        add(menu);
    }
}
