package com.kodilla.clothesfactory_frontend.form.admin;

import com.kodilla.clothesfactory_frontend.domain.PaymentHistory;
import com.kodilla.clothesfactory_frontend.service.PaymentHistoryService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class PaymentHistoryForm extends VerticalLayout {

    public PaymentHistoryForm() {
        Grid<PaymentHistory> paymentHistoryGrid = new Grid<>(PaymentHistory.class);
        paymentHistoryGrid.setColumns("paymentTime", "orderId", "userMail", "price");
        add(new Text("Payment History"));
        add(paymentHistoryGrid);
        PaymentHistoryService paymentHistoryService = PaymentHistoryService.getInstance();
        paymentHistoryGrid.setItems(paymentHistoryService.getPaymentHistory());
    }
}