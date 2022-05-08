package com.kodilla.clothesfactory_frontend.form.account;

import com.kodilla.clothesfactory_frontend.domain.ExchangeRate;
import com.kodilla.clothesfactory_frontend.service.ExchangeRateService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import java.math.BigDecimal;

public class CurrencyForm extends VerticalLayout {
    private final ExchangeRateService exchangeRateService = ExchangeRateService.getInstance();
    private ExchangeRate exchangeRate;
    private BigDecimal currencyTotalPrice;
    private final Text price = new Text("");
    private final Button euro = new Button("EUR");
    private final Button dollar = new Button("USD");
    private final Button pound = new Button("GBP");

    public CurrencyForm(BigDecimal totalPrice) {
        euro.addClickListener(event -> showEuro(totalPrice, price));
        dollar.addClickListener(event -> showDollar(totalPrice, price));
        pound.addClickListener(event -> showPound(totalPrice, price));
        HorizontalLayout buttons = new HorizontalLayout(euro, dollar, pound);
        add(buttons, price);
    }

    private void showEuro(BigDecimal totalPrice, Text price) {
        exchangeRate = exchangeRateService.getExchange("EUR", "PLN");
        currencyTotalPrice = totalPrice.multiply(exchangeRate.getCurrencyRate());
        price.setText("Price in EUR: " + currencyTotalPrice.doubleValue() + " EUR");
    }

    private void showDollar(BigDecimal totalPrice, Text price) {
        exchangeRate = exchangeRateService.getExchange("USD", "PLN");
        currencyTotalPrice = totalPrice.multiply(exchangeRate.getCurrencyRate());
        price.setText("Price in USD: " + currencyTotalPrice.doubleValue() + " USD");
    }

    private void showPound(BigDecimal totalPrice, Text price) {
        exchangeRate = exchangeRateService.getExchange("GBP", "PLN");
        currencyTotalPrice = totalPrice.multiply(exchangeRate.getCurrencyRate());
        price.setText("Price in GBP: " + currencyTotalPrice.doubleValue() + " GBP");
    }
}