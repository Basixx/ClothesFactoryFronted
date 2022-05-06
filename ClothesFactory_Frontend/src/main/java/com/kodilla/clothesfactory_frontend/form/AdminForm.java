package com.kodilla.clothesfactory_frontend.form;

import com.kodilla.clothesfactory_frontend.domain.Cloth;
import com.kodilla.clothesfactory_frontend.domain.Order;
import com.kodilla.clothesfactory_frontend.domain.User;
import com.kodilla.clothesfactory_frontend.service.AdminTokenService;
import com.kodilla.clothesfactory_frontend.service.ClothService;
import com.kodilla.clothesfactory_frontend.service.OrderService;
import com.kodilla.clothesfactory_frontend.service.UserService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import org.springframework.web.client.RestClientException;

public class AdminForm extends VerticalLayout {
    private final ClothService clothService = ClothService.getInstance();
    private final OrderService orderService = OrderService.getInstance();
    private final UserService userService = UserService.getInstance();
    private final AdminTokenService adminTokenService = AdminTokenService.getInstance();
    private final Button submit = new Button("SUBMIT", event-> authenticateAdmin());

    private final Grid<Cloth> clothGrid = new Grid<>(Cloth.class);
    private final Grid<Order> orderGrid = new Grid<>(Order.class);
    private final Grid<User> userGrid = new Grid<>(User.class);

    private final Button clothes = new Button("Show All Clothes", e -> showClothes());
    private final Button orders = new Button("Show All Orders", e -> showOrders());
    private final Button users = new Button("Show All Users", e  -> showUsers());
    private final Button setToPaid = new Button("PAID", e -> setOrderToPaid());
    private final Button setToSent = new Button("SENT", e -> setOrderToSent());
    private final Button previousPage = new Button("Previous Page", event -> previous());
    private final Button token = new Button("Generate Token", event -> generateToken());
    private final Button logOut = new Button("Log Out", event -> logout());
    private final TextField authentication = new TextField("AUTHENTICATION");
    private final HorizontalLayout toolbar = new HorizontalLayout(clothes, orders, users, logOut);
    private final VerticalLayout view = new VerticalLayout(new Text("ADMIN"), toolbar);
    public AdminForm() {

        add(new VerticalLayout(new Text("ADMIN"), previousPage, token, authentication, submit));
    }

    private void showAdminView() {
        removeAll();
        add(view);
    }

    private void showClothes() {
        showAdminView();
        clothGrid.setColumns("fashion", "color", "print", "font", "printColor", "size", "quantity", "price");
        add(new Text("All Clothes:"));
        add(clothGrid);
        clothGrid.setItems(clothService.getAllClothes());
    }

    private void showOrders() {
        showAdminView();
        orderGrid.setColumns("orderDate", "totalOrderPrice", "paid", "sent");
        add(new Text("All Orders:"));
        add(orderGrid);
        refreshAllOrders();

        orderGrid.asSingleSelect().addValueChangeListener(event -> {
            if( orderGrid.asSingleSelect().getValue() == null) {
                return;
            }
            int orderId = orderGrid.asSingleSelect().getValue().getId().intValue();
            clothGrid.setItems(clothService.getClothesFromOrder(orderId));
            add(clothGrid);
            add(new HorizontalLayout(setToPaid, setToSent));
        });
    }

    private void showUsers() {
        showAdminView();
        userGrid.setColumns("name", "surname", "phoneNumber", "emailAddress");
        add(new Text("All Users:"));
        add(userGrid);
        userGrid.setItems(userService.getAllUsers());
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

    private void authenticateAdmin() {
        String token = authentication.getValue();

        if(adminTokenService.existsToken(token)) {
            showAdminView();
        } else {
            Notification.show("Token does not exist");
        }
    }

    private void logout() {
        UI.getCurrent().getPage().reload();
    }

    private void previous() {
        UI.getCurrent().getPage().reload();
    }

    private void generateToken() {
        adminTokenService.createToken();
    }
}