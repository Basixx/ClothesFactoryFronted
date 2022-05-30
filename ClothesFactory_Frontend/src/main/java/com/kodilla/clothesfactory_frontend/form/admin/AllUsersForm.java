package com.kodilla.clothesfactory_frontend.form.admin;

import com.kodilla.clothesfactory_frontend.domain.Order;
import com.kodilla.clothesfactory_frontend.domain.User;
import com.kodilla.clothesfactory_frontend.service.OrderService;
import com.kodilla.clothesfactory_frontend.service.UserService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class AllUsersForm extends VerticalLayout {

    private final OrderService orderService = OrderService.getInstance();
    private final Grid<User> userGrid = new Grid<>(User.class);
    private final Grid<Order> orderGrid = new Grid<>(Order.class);

    public AllUsersForm() {
        userGrid.setColumns("name", "surname", "phoneNumber", "emailAddress", "password",
                "street", "streetAndApartmentNumber", "city", "postCode");
        userGrid.getColumns().forEach(column -> column.setAutoWidth(true));
        add(new Text("All Users:"));
        add(userGrid);
        UserService userService = UserService.getInstance();
        userGrid.setItems(userService.getAllUsers());

        userGrid.asSingleSelect().addValueChangeListener(event -> {
            if(userGrid.asSingleSelect().getValue() == null) {
                return;
            }
            int userId = userGrid.asSingleSelect().getValue().getId().intValue();
            orderGrid.setColumns("id", "orderDate", "totalOrderPrice", "shipmentCompanyName", "shippingPrice", "deliveryDays");
            orderGrid.getColumns().forEach(column -> column.setAutoWidth(true));
            orderGrid.setItems(orderService.getOrdersByUser(userId));
            add(orderGrid);
        });
    }
}