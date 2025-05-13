package com.example.recuperacionhackaton1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private ApplicationEventPublisher publisher;

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody OrderDTO orderDTO) {
        OrderCreatedEvent event = new OrderCreatedEvent(this, orderDTO.getId(), orderDTO.getEmail(), orderDTO.getProductos());
        publisher.publishEvent(event);
        return ResponseEntity.ok("Pedido recibido y evento publicado");
    }
}
