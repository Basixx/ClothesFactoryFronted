package com.kodilla.clothesfactory_frontend.service;

import com.kodilla.clothesfactory_frontend.configuration.client.OrdersClient;
import com.kodilla.clothesfactory_frontend.domain.Order;
import java.util.List;

public class OrderService {

    private static OrderService orderService;
    private final OrdersClient ordersClient = OrdersClient.getInstance();

    public static OrderService getInstance() {
        if (orderService == null) {
            orderService = new OrderService();
        }
        return orderService;
    }

    public List<Order> getAllOrders() {
        return ordersClient.getAllOrders();
    }

    public List<Order> getOrdersByUser(int userId) {
        return ordersClient.getOrderByUser(userId);
    }

    public void createOrder(int userId) {
        ordersClient.createOrder(userId);
    }

    public void setOrderToPaid(int orderId) {
        ordersClient.setOrderToPaid(orderId);
    }

    public void setOrderToSent(int orderId) {
        ordersClient.setOrderToSent(orderId);
    }
}
