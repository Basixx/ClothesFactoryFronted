package com.kodilla.clothesfactory_frontend.form;

import com.kodilla.clothesfactory_frontend.domain.Cloth;
import com.kodilla.clothesfactory_frontend.domain.Order;
import com.kodilla.clothesfactory_frontend.domain.User;
import com.kodilla.clothesfactory_frontend.service.ClothService;
import com.kodilla.clothesfactory_frontend.service.OrderService;
import com.kodilla.clothesfactory_frontend.service.UserService;
import com.kodilla.clothesfactory_frontend.views.MainView;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;


public class AdminForm extends FormLayout {
    private MainView mainView;
    private ClothService clothService = ClothService.getInstance();
    private OrderService orderService = OrderService.getInstance();
    private UserService userService = UserService.getInstance();

    private TextField authentication = new TextField("AUTHENTICATION");
    Button submit = new Button("SUBMIT", event-> showAdminView());

    private Grid<Cloth> clothGrid = new Grid<>(Cloth.class);
    private Grid<Order> orderGrid = new Grid<>(Order.class);
    private Grid<User> userGrid = new Grid<>(User.class);

    private Button clothes = new Button("Show All Clothes", e -> showClothes());
    private Button orders = new Button("Show All Orders", e -> showOrders());
    private Button users = new Button("Show All Users", e  -> showUsers());

    HorizontalLayout toolbar = new HorizontalLayout(clothes, orders, users);
    VerticalLayout view = new VerticalLayout(new Text("ADMIN"), toolbar);
    public AdminForm(MainView mainView) {
        this.mainView = mainView;
        add(authentication, submit);
    }

    private void showAdminView() {
        removeAll();
        add(view);
    }

    private void showClothes() {
        showAdminView();
        clothGrid.setColumns("fashion", "color", "print", "font", "printColor", "size", "quantity");
        add(clothGrid);
        clothGrid.setItems(clothService.getClothes());
    }

    private void showOrders() {
        showAdminView();
        orderGrid.setColumns("orderDate", "totalOrderPrice");
        add(orderGrid);
        orderGrid.setItems(orderService.getOrders());
    }

    private void showUsers() {
        showAdminView();
        userGrid.setColumns("name", "surname", "phoneNumber", "emailAddress");
        add(userGrid);
        userGrid.setItems(userService.getUsers());
    }
}