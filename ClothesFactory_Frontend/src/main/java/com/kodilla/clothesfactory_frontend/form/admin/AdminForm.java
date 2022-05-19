package com.kodilla.clothesfactory_frontend.form.admin;

import com.kodilla.clothesfactory_frontend.service.AdminTokenService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

public class AdminForm extends VerticalLayout {

    private final AdminTokenService adminTokenService = AdminTokenService.getInstance();
    private final Button submit = new Button("SUBMIT", event-> authenticateAdmin());
    private final Button clothes = new Button("Show All Clothes", e -> showClothes());
    private final Button orders = new Button("Show All Orders", e -> showOrders());
    private final Button users = new Button("Show All Users", e  -> showUsers());
    private final Button loginHistory = new Button("Show Login History", e -> showLoginHistory());
    private final Button previousPage = new Button("Previous Page", event -> previous());
    private final Button token = new Button("Generate Token", event -> generateToken());
    private final Button logOut = new Button("Log Out", event -> logout());
    private final TextField authentication = new TextField("AUTHENTICATION");
    private final HorizontalLayout toolbar = new HorizontalLayout(clothes, orders, users, loginHistory, logOut);
    private final VerticalLayout view = new VerticalLayout(new Text("ADMIN"), toolbar);
    public AdminForm() {
        add(new VerticalLayout(new Text("ADMIN"), previousPage, token, authentication, submit));
    }

    private void showAdminView() {
        removeAll();
        add(view);
    }

    private void showClothes() {
        showAdminView();
        add(new AllClothesForm());
    }

    private void showOrders() {
        showAdminView();
        add(new AllOrdersForm());
    }

    private void showUsers() {
        showAdminView();
        add(new AllUsersForm());
    }

    private void showLoginHistory() {
        showAdminView();
        add(new LoginHistoryForm());
    }

    private void authenticateAdmin() {
        String token = authentication.getValue();

        if(adminTokenService.existsToken(token)) {
            showAdminView();
            adminTokenService.deleteTokens();
        } else {
            Notification.show("Token does not exist");
        }
    }

    private void logout() {
        UI.getCurrent().getPage().reload();
    }

    private void previous() {
        UI.getCurrent().getPage().reload();
    }

    private void generateToken() {
        adminTokenService.createToken();
    }
}