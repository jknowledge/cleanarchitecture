package de.jknowledge.cleanarchitecture.domain.aggregate.order;

import de.jknowledge.cleanarchitecture.domain.dddbase.DomainEvent;
import org.springframework.context.ApplicationEvent;

public class OrderCreatedEvent extends ApplicationEvent implements DomainEvent {

    private OrderEntity order;

    public OrderCreatedEvent(Object source, OrderEntity order) {
        super(source);
        this.order = order;
    }

    public OrderEntity getOrder() {
        return order;
    }
}
