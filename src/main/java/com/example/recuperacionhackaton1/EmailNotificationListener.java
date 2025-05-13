package com.example.recuperacionhackaton1;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class EmailNotificationListener {

    @EventListener
    public void handle(OrderCreatedEvent event) {
        log.info("Enviando correo de confirmación a: {}", event.getEmail());
    }
}
