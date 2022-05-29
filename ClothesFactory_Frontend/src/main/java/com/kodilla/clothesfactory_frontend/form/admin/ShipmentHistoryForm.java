package com.kodilla.clothesfactory_frontend.form.admin;

import com.kodilla.clothesfactory_frontend.domain.ShipmentHistory;
import com.kodilla.clothesfactory_frontend.service.ShipmentHistoryService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class ShipmentHistoryForm extends VerticalLayout {

    public ShipmentHistoryForm() {
        Grid<ShipmentHistory> shipmentHistoryGrid = new Grid<>(ShipmentHistory.class);
        shipmentHistoryGrid.setColumns("shipmentTime", "orderId", "userMail", "shippingCompany");
        add(new Text("Shipment History"));
        add(shipmentHistoryGrid);
        ShipmentHistoryService shipmentHistoryService = ShipmentHistoryService.getInstance();
        shipmentHistoryGrid.setItems(shipmentHistoryService.getShipmentHistory());
    }
}
