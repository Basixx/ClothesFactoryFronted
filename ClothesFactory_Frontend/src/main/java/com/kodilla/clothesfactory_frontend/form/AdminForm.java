package com.kodilla.clothesfactory_frontend.form;

import com.kodilla.clothesfactory_frontend.domain.Cloth;
import com.kodilla.clothesfactory_frontend.domain.Order;
import com.kodilla.clothesfactory_frontend.domain.User;
import com.kodilla.clothesfactory_frontend.service.ClothService;
import com.kodilla.clothesfactory_frontend.service.OrderService;
import com.kodilla.clothesfactory_frontend.service.UserService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

public class AdminForm extends FormLayout {
    private final ClothService clothService = ClothService.getInstance();
    private final OrderService orderService = OrderService.getInstance();
    private final UserService userService = UserService.getInstance();

    Button submit = new Button("SUBMIT", event-> showAdminView());

    private final Grid<Cloth> clothGrid = new Grid<>(Cloth.class);
    private final Grid<Order> orderGrid = new Grid<>(Order.class);
    private final Grid<User> userGrid = new Grid<>(User.class);

    private final Button clothes = new Button("Show All Clothes", e -> showClothes());
    private final Button orders = new Button("Show All Orders", e -> showOrders());
    private final Button users = new Button("Show All Users", e  -> showUsers());
    private final Button setToPaid = new Button("PAID", e -> setOrderToPaid());
    private final Button setToSent = new Button("SENT", e -> setOrderToSent());

    HorizontalLayout toolbar = new HorizontalLayout(clothes, orders, users);
    VerticalLayout view = new VerticalLayout(new Text("ADMIN"), toolbar);
    public AdminForm() {
        TextField authentication = new TextField("AUTHENTICATION");
        add(authentication, submit);
    }

    private void showAdminView() {
        removeAll();
        add(view);
    }

    private void showClothes() {
        showAdminView();
        clothGrid.setColumns("fashion", "color", "print", "font", "printColor", "size", "quantity", "price");
        add(clothGrid);
        clothGrid.setItems(clothService.getAllClothes());
    }

    private void showOrders() {
        showAdminView();
        orderGrid.setColumns("orderDate", "totalOrderPrice", "paid", "sent");
        add(orderGrid);
        refreshAllOrders();

        orderGrid.asSingleSelect().addValueChangeListener(event -> {
//            orderId = orderGrid.asSingleSelect().getValue().getId().intValue();
            clothGrid.setItems(clothService.getClothesFromOrder(orderGrid.asSingleSelect().getValue().getId().intValue()));
            add(clothGrid);
            add(new HorizontalLayout(setToPaid, setToSent));
        });
    }

    private void showUsers() {
        showAdminView();
        userGrid.setColumns("name", "surname", "phoneNumber", "emailAddress");
        add(userGrid);
        userGrid.setItems(userService.getAllUsers());
    }

    private void refreshAllOrders() {
        orderGrid.setItems(orderService.getAllOrders());
    }

    private void setOrderToPaid() {
        orderService.setOrderToPaid(orderGrid.asSingleSelect().getValue().getId().intValue());
        refreshAllOrders();
    }

    private void setOrderToSent() {
        orderService.setOrderToSent(orderGrid.asSingleSelect().getValue().getId().intValue());
        refreshAllOrders();
    }
}