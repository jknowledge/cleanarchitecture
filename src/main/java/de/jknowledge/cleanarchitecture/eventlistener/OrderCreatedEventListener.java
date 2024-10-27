package de.jknowledge.cleanarchitecture.eventlistener;

import de.jknowledge.cleanarchitecture.domain.aggregate.order.OrderCreatedEvent;
import de.jknowledge.cleanarchitecture.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class OrderCreatedEventListener implements ApplicationListener<OrderCreatedEvent> {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void onApplicationEvent(OrderCreatedEvent event) {
        orderRepository.save(event.getOrder());
    }
}
