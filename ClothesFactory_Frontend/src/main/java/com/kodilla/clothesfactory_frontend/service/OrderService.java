package com.kodilla.clothesfactory_frontend.service;

import com.kodilla.clothesfactory_frontend.configuration.client.OrdersClient;
import com.kodilla.clothesfactory_frontend.domain.Order;
import org.springframework.web.client.RestClientException;
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
        try {
            ordersClient.createOrder(userId);
        } catch (RestClientException e) {
            throw e;
        }

    }

    public void setOrderToPaid(int orderId) {
        try {
            ordersClient.setOrderToPaid(orderId);
        } catch (RestClientException e) {
            throw e;
        }

    }

    public void setOrderToSent(int orderId) {
        try {
            ordersClient.setOrderToSent(orderId);
        } catch (RestClientException e) {
            throw e;
        }

    }
}