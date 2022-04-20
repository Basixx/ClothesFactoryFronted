package com.kodilla.clothesfactory_frontend.service;

import com.kodilla.clothesfactory_frontend.domain.Order;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class OrderService {
    private List<Order> orders;

    private static OrderService orderService;

    private OrderService(List<Order> orders){
//        this.orders = orders;
        this.orders = exampleData();
    }

    public static OrderService getInstance() {
        if (orderService == null) {
            orderService = new OrderService(new ArrayList<>());
        }
        return orderService;
    }

    public List<Order> getOrders() {
        return new ArrayList<>(orders);
    }

    public void addOrder(Order order) {
        this.orders.add(order);
    }

    private List<Order> exampleData() {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order(LocalDate.of(1998, 2, 22), new BigDecimal(50)));
        orders.add(new Order(LocalDate.of(2022, 5, 3), new BigDecimal(80)));
//        orders.add(new Order("50", "22.02.2022"));
//        orders.add(new Order("100", "05.03.2022"));
        return orders;
    }

}
