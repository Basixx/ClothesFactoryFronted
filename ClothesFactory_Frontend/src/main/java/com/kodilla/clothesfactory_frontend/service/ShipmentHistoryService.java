package com.kodilla.clothesfactory_frontend.service;

import com.kodilla.clothesfactory_frontend.configuration.client.ShipmentHistoryClient;
import com.kodilla.clothesfactory_frontend.domain.ShipmentHistory;

import java.util.List;

public class ShipmentHistoryService {

    private static ShipmentHistoryService shipmentHistoryService;
    private final ShipmentHistoryClient shipmentHistoryClient = ShipmentHistoryClient.getInstance();

    public static ShipmentHistoryService getInstance() {
        if(shipmentHistoryService == null) {
            shipmentHistoryService = new ShipmentHistoryService();
        }
        return shipmentHistoryService;
    }

    public List<ShipmentHistory> getShipmentHistory() {
        return shipmentHistoryClient.getShipmentHistory();
    }
}
