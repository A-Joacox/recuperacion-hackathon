package com.example.recuperacionhackaton1;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Log4j2
public class InventoryUpdateListener {

    @Autowired
    private InventoryService inventoryService;

    @EventListener
    public void handle(OrderCreatedEvent event) {
        log.info("Reduciendo stock para productos: {}", event.getProductos());

        Map<String, Integer> updatedStock = inventoryService.reduceStock(event.getProductos());

        log.info("Stock actualizado después del pedido {}: {}", event.getOrderId(), updatedStock);
    }
}