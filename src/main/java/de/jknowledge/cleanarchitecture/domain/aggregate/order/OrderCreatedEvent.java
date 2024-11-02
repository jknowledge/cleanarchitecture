package de.jknowledge.cleanarchitecture.domain.aggregate.order;

import de.jknowledge.cleanarchitecture.domain.dddbase.IDomainEvent;
import org.springframework.context.ApplicationEvent;

public class OrderCreatedEvent extends ApplicationEvent implements IDomainEvent {

    private OrderEntity order;

    public OrderCreatedEvent(Object source, OrderEntity order) {
        super(source);
        this.order = order;
    }

    public OrderEntity getOrder() {
        return order;
    }
}
