package com.kodilla.clothesfactory_frontend.form.admin;

import com.kodilla.clothesfactory_frontend.domain.SignInHistory;
import com.kodilla.clothesfactory_frontend.service.SignInHistoryService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class SignInHistoryForm extends VerticalLayout {

    public SignInHistoryForm() {
        Grid<SignInHistory> signInHistoryGrid = new Grid<>(SignInHistory.class);
        signInHistoryGrid.setColumns("signInTime", "userMail", "userNumber");
        signInHistoryGrid.getColumns().forEach(column -> column.setAutoWidth(true));
        add(new Text("Sign In History"));
        add(signInHistoryGrid);
        SignInHistoryService signInHistoryService = SignInHistoryService.getInstance();
        signInHistoryGrid.setItems(signInHistoryService.getSignInHistory());
    }
}
