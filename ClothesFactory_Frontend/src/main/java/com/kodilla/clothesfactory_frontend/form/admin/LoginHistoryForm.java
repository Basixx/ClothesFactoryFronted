package com.kodilla.clothesfactory_frontend.form.admin;

import com.kodilla.clothesfactory_frontend.domain.LoginHistory;
import com.kodilla.clothesfactory_frontend.service.LoginHistoryService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class LoginHistoryForm extends VerticalLayout {

    private final LoginHistoryService loginHistoryService = LoginHistoryService.getInstance();
    private final Grid<LoginHistory> loginHistoryGrid = new Grid<>(LoginHistory.class);

    public LoginHistoryForm() {
        loginHistoryGrid.setColumns("loginTime", "userMail", "succeed");
        add(new Text("Login History"));
        add(loginHistoryGrid);
        loginHistoryGrid.setItems(loginHistoryService.getLoginHistory());
    }
}
