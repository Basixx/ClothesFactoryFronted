package com.kodilla.clothesfactory_frontend.form.auxiliary;

import com.kodilla.clothesfactory_frontend.domain.Cloth;
import com.kodilla.clothesfactory_frontend.domain.Order;
import com.kodilla.clothesfactory_frontend.service.ClothService;
import com.kodilla.clothesfactory_frontend.service.OrderService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class OrdersForm extends VerticalLayout {

    private final ClothService clothService = ClothService.getInstance();
    private final OrderService orderService = OrderService.getInstance();
    private final Grid<Order> orderGrid = new Grid<>(Order.class);
    private final Grid<Cloth> clothGrid = new Grid<>(Cloth.class);

    public OrdersForm(int userId) {
        orderGrid.setColumns("orderDate", "totalOrderPrice", "paid", "sent");
        add(new Text("My Orders"));
        add(orderGrid);
        refreshOrders(userId);

        orderGrid.asSingleSelect().addValueChangeListener(event -> {

            clothGrid.setItems(clothService.getClothesFromOrder(orderGrid.asSingleSelect().getValue().getId().intValue()));
            add(clothGrid);
        });
    }

    private void refreshOrders(int userID) {
        orderGrid.setItems(orderService.getOrdersByUser(userID));
    }

}
