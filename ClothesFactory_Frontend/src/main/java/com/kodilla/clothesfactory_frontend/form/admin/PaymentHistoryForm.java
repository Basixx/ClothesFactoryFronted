package com.kodilla.clothesfactory_frontend.form.admin;

import com.kodilla.clothesfactory_frontend.domain.PaymentHistory;
import com.kodilla.clothesfactory_frontend.service.PaymentHistoryService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class PaymentHistoryForm extends VerticalLayout {

    private final PaymentHistoryService paymentHistoryService = PaymentHistoryService.getInstance();
    private final Grid<PaymentHistory> paymentHistoryGrid = new Grid<>(PaymentHistory.class);

    public PaymentHistoryForm() {
        paymentHistoryGrid.setColumns("paymentTime", "orderId", "userMail", "price");
        add(new Text("Payment History"));
        add(paymentHistoryGrid);
        paymentHistoryGrid.setItems(paymentHistoryService.getPaymentHistory());
    }
}