package de.jknowledge.cleanarchitecture.unit.application.eventlistener;

import de.jknowledge.cleanarchitecture.application.eventlistener.OrderCreatedEventListener;
import de.jknowledge.cleanarchitecture.domain.aggregate.order.OrderCreatedEvent;
import de.jknowledge.cleanarchitecture.domain.aggregate.order.OrderEntityTestDataBuilder;
import de.jknowledge.cleanarchitecture.infrastructure.persistence.repository.IOrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ExtendWith(MockitoExtension.class)
public class OrderCreatedEventListenerTests {

    @InjectMocks
    private OrderCreatedEventListener listener;

    @Mock
    private IOrderRepository orderRepository;

    @Test
    public void testOnApplicationEvent() {
        // arrange
        OrderCreatedEvent event = new OrderCreatedEvent(this, OrderEntityTestDataBuilder.dagobertDuckOrder());

        // act
        listener.onApplicationEvent(event);

        // assert
        verify(orderRepository, times(1)).save(event.getOrder());
    }
}
