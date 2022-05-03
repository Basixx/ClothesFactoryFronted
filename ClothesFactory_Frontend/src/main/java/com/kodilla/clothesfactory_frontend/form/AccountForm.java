package com.kodilla.clothesfactory_frontend.form;

import com.kodilla.clothesfactory_frontend.domain.Cloth;
import com.kodilla.clothesfactory_frontend.domain.Order;
import com.kodilla.clothesfactory_frontend.domain.User;
import com.kodilla.clothesfactory_frontend.service.CartService;
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
import com.vaadin.flow.data.binder.Binder;

import java.math.BigDecimal;

public class AccountForm extends FormLayout {
    private MainView mainView;
    private ClothService clothService = ClothService.getInstance();
    private OrderService orderService = OrderService.getInstance();
    private UserService userService = UserService.getInstance();
    private CartService cartService = CartService.getInstance();
    Button createNewOrder = new Button("Create New Order",  event -> showClothes());
    Button myOrders = new Button("My Orders", event -> showOrders());
    Button accountSettings = new Button("Account Settings", event -> showAccountSettings());
    private Button addNewCloth = new Button("Add new cloth");
    private Button createOrder = new Button("Create Order");
    private Grid<Cloth> clothGrid = new Grid<>(Cloth.class);
    private Grid<Order> orderGrid = new Grid<>(Order.class);
    private Grid<User> userGrid = new Grid<>(User.class);
    private ClothForm clothForm = new ClothForm(this);
    private ChangeUserCredentialsForm changeUserCredentialsForm = new ChangeUserCredentialsForm(this);
    private BigDecimal totalPrice = cartService.getCartByUser(98).getTotalPrice();
    private Text price = new Text("Total price: " + totalPrice);
    private Binder<Order> orderBinder = new Binder<>(Order.class);
    HorizontalLayout toolbar = new HorizontalLayout(createNewOrder, myOrders, accountSettings);
    VerticalLayout view = new VerticalLayout(new Text("ACCOUNT"), toolbar);

    public AccountForm(MainView mainView) {
        this.mainView = mainView;
        add(view);
    }

    private void showAccountView() {
        removeAll();
        add(view);
    }

    private void showClothes() {

//        int totalPrice = cartService.getCartByUser(98).size();
       // Text price = new Text("Total price: " + totalPrice);
        clothForm.setCloth(null);
        showAccountView();

        clothGrid.setColumns("fashion", "color", "print", "font", "printColor", "size", "quantity", "price");
        addNewCloth.addClickListener(e -> {
            clothGrid.asSingleSelect().clear();
            clothForm.setCloth(new Cloth());
            clothForm.buttons.remove(clothForm.save);
            clothForm.buttons.add(clothForm.add);
        });
        add(addNewCloth);
        VerticalLayout clothLayout = new VerticalLayout(clothGrid, price, clothForm);
        add(clothLayout);
        setSizeFull();
        refreshClothes();
        clothGrid.asSingleSelect().addValueChangeListener(event -> {
            clothForm.setCloth(clothGrid.asSingleSelect().getValue());
            clothForm.buttons.remove(clothForm.add);
            clothForm.buttons.add(clothForm.save);
        });
        add(createOrder);
    }

    private void showOrders() {
//        Order order = orderBinder.getBean();
//        System.out.println(order.getId());
        showAccountView();
        orderGrid.setColumns("orderDate", "totalOrderPrice", "paid", "sent");
        add(orderGrid);
        refreshOrders(98);
        orderGrid.asSingleSelect().addValueChangeListener(event -> {
//            Order order = orderBinder.getBean();
//            System.out.println(order.getId());
//            Long id = clothGrid.asSingleSelect().getValue().getId();
            clothGrid.setItems(clothService.getClothesFromOrder(99));  //tutaj dać by ORDER
            add(clothGrid);
//            setOrder(null);
        });
    }

    private void showAccountSettings() {
        changeUserCredentialsForm.setUser(null);
        showAccountView();
        userGrid.setColumns("name", "surname", "phoneNumber", "emailAddress", "password");
        VerticalLayout accountLayout = new VerticalLayout(userGrid, changeUserCredentialsForm);
        add(accountLayout);
        setSizeFull();;
        refreshUser();
        userGrid.asSingleSelect().addValueChangeListener(event -> changeUserCredentialsForm.setUser(userGrid.asSingleSelect().getValue()));
    }

    public void refreshClothes() {
        clothGrid.setItems(clothService.getClothesFromUserCart(98));    //HARDCODED
        totalPrice = cartService.getCartByUser(98).getTotalPrice();
        price.setText("Total price: " + totalPrice);
    }
    public void refreshOrders(int userId) {
        orderGrid.setItems(orderService.getOrdersByUser(userId));
    }
    public void refreshUser() {
        userGrid.setItems(userService.getUsers());  //tu user by ID
    }

    public void setOrder(Order order) {
        orderBinder.setBean(order);

        if (order == null) {
            setVisible(false);
        } else {
            setVisible(true);
        }
    }
}
