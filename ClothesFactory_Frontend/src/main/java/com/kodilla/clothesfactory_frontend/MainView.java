package com.kodilla.clothesfactory_frontend;

import com.kodilla.clothesfactory_frontend.domain.Cloth;
import com.kodilla.clothesfactory_frontend.domain.Order;
import com.kodilla.clothesfactory_frontend.form.ClothForm;
import com.kodilla.clothesfactory_frontend.service.ClothService;
import com.kodilla.clothesfactory_frontend.service.OrderService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route
public class MainView extends VerticalLayout {
    private OrderService orderService = OrderService.getInstance();
    private ClothService clothService = ClothService.getInstance();
    private Grid<Order> orderGrid = new Grid<>(Order.class);
    private Grid<Cloth> clothGrid = new Grid<>(Cloth.class);
    private ClothForm clothForm = new ClothForm(this);

    private Button clothes = new Button("Show Clothes", e -> showClothes());
    private Button orders = new Button("Show Orders", e -> showOrders());
    private Button addNewCloth = new Button("Add new cloth");

    HorizontalLayout toolbar = new HorizontalLayout(clothes, orders);

    public MainView() {
        add(toolbar);
        clothForm.setCloth(null);
    }

    private void showOrders() {
        removeAll();
        add(toolbar);
        orderGrid.setColumns("orderDate", "totalOrderPrice");
        add(orderGrid);
        setSizeFull();
        refreshOrders();
    }
    public void refreshOrders() {
        orderGrid.setItems(orderService.getOrders());
    }
    private void showClothes() {

        removeAll();
        add(toolbar);
        add(addNewCloth);
        clothGrid.setColumns("fashion", "color", "print", "font", "printColor", "size", "quantity");
        HorizontalLayout clothLayout = new HorizontalLayout(clothGrid, clothForm);
        clothLayout.setSizeFull();
        add(clothLayout);
        setSizeFull();
        refreshClothes();
        clothGrid.asSingleSelect().addValueChangeListener(event -> clothForm.setCloth(clothGrid.asSingleSelect().getValue()));
        addNewCloth.addClickListener(e -> {
            clothGrid.asSingleSelect().clear();
            clothForm.setCloth(new Cloth());
        });
    }
    public void refreshClothes() {
        clothGrid.setItems(clothService.getClothes());
    }
}