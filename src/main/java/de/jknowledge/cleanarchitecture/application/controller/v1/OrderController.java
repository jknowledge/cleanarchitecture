package de.jknowledge.cleanarchitecture.application.controller.v1;

import de.jknowledge.cleanarchitecture.domain.aggregate.order.OrderCreatedEvent;
import de.jknowledge.cleanarchitecture.domain.aggregate.order.OrderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person/v1.0")
public class OrderController {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity createOrder(@RequestBody OrderModel order) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.createOrder(order.getFirstname(),
                order.getLastname(),
                order.getZip(),
                order.getCity(),
                order.getStreet(),
                order.getHouseNumber(),
                order.getArticleList());
        eventPublisher.publishEvent(new OrderCreatedEvent(this, orderEntity));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
