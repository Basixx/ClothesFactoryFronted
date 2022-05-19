package com.kodilla.clothesfactory_frontend.form.admin;

import com.kodilla.clothesfactory_frontend.domain.Cloth;
import com.kodilla.clothesfactory_frontend.domain.Order;
import com.kodilla.clothesfactory_frontend.service.ClothService;
import com.kodilla.clothesfactory_frontend.service.OrderService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.springframework.web.client.RestClientException;

public class AllOrdersForm extends VerticalLayout {

    private final ClothService clothService = ClothService.getInstance();
    private final OrderService orderService = OrderService.getInstance();

    private final Grid<Cloth> clothGrid = new Grid<>(Cloth.class);
    private final Grid<Order> orderGrid = new Grid<>(Order.class);

    private final Button setToPaid = new Button("PAID", e -> setOrderToPaid());
    private final Button setToSent = new Button("SENT", e -> setOrderToSent());

    public AllOrdersForm() {
        orderGrid.setColumns("orderDate", "totalOrderPrice", "paid", "sent", "shipmentCompanyName", "shippingPrice", "deliveryDays", "address");
        add(new Text("All Orders:"));
        add(orderGrid);
        refreshAllOrders();

        orderGrid.asSingleSelect().addValueChangeListener(event -> {
            if (orderGrid.asSingleSelect().getValue() == null) {
                return;
            }
            int orderId = orderGrid.asSingleSelect().getValue().getId().intValue();
            clothGrid.setItems(clothService.getClothesFromOrder(orderId));
            add(clothGrid);
            add(new HorizontalLayout(setToPaid, setToSent));
        });
    }

    private void refreshAllOrders() {
        orderGrid.setItems(orderService.getAllOrders());
    }

    private void setOrderToPaid() {
        try {
            orderService.setOrderToPaid(orderGrid.asSingleSelect().getValue().getId().intValue());
        } catch (RestClientException e) {
            Notification.show(e.getMessage());
        } finally {
            refreshAllOrders();
        }
    }

    private void setOrderToSent() {
        try {
            orderService.setOrderToSent(orderGrid.asSingleSelect().getValue().getId().intValue());
        } catch (RestClientException e) {
            Notification.show(e.getMessage());
        } finally {
            refreshAllOrders();
        }
    }
}