package com.kodilla.clothesfactory_frontend.service;

import com.kodilla.clothesfactory_frontend.configuration.client.PaymentHistoryClient;
import com.kodilla.clothesfactory_frontend.domain.PaymentHistory;
import java.util.List;

public class PaymentHistoryService {

    private static PaymentHistoryService paymentHistoryService;
    private final PaymentHistoryClient paymentHistoryClient = PaymentHistoryClient.getInstance();

    public static PaymentHistoryService getInstance() {
        if(paymentHistoryService == null) {
            paymentHistoryService = new PaymentHistoryService();
        }
        return paymentHistoryService;
    }

    public List<PaymentHistory> getPaymentHistory() {
        return paymentHistoryClient.getPaymentHistory();
    }
}