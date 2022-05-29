package com.kodilla.clothesfactory_frontend.form.admin;

import com.kodilla.clothesfactory_frontend.domain.SignInHistory;
import com.kodilla.clothesfactory_frontend.service.SignInHistoryService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class SignInHistoryForm extends VerticalLayout {

    private final SignInHistoryService signInHistoryService = SignInHistoryService.getInstance();
    private final Grid<SignInHistory> signInHistoryGrid = new Grid<>(SignInHistory.class);

    public SignInHistoryForm() {
        signInHistoryGrid.setColumns("signInTime", "userMail", "userNumber");
        add(new Text("Sign In History"));
        add(signInHistoryGrid);
        signInHistoryGrid.setItems(signInHistoryService.getSignInHistory());
    }
}
