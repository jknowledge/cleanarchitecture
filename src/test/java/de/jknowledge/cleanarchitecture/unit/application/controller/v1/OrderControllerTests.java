package de.jknowledge.cleanarchitecture.unit.application.controller.v1;

import de.jknowledge.cleanarchitecture.application.controller.v1.OrderController;
import de.jknowledge.cleanarchitecture.application.controller.v1.OrderModel;
import de.jknowledge.cleanarchitecture.domain.aggregate.order.OrderCreatedEvent;
import de.jknowledge.cleanarchitecture.domain.aggregate.order.OrderEntity;
import de.jknowledge.cleanarchitecture.domain.aggregate.order.OrderEntityTestDataBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ExtendWith(MockitoExtension.class)
public class OrderControllerTests {

    @InjectMocks
    private OrderController orderController;

    @Mock
    private ApplicationEventPublisher eventPublisher;

    @Captor
    private ArgumentCaptor<OrderCreatedEvent> ordercCreatedEventCaptor;

    @Test
    public void testCreateOrder() {
        // arrange
        OrderEntity orderEntity = OrderEntityTestDataBuilder.buildMickeyMouseOrder();
        OrderModel orderModel = new OrderModel();
        orderModel.setCity(orderEntity.getCity());
        orderModel.setFirstname(orderEntity.getFirstname());
        orderModel.setHouseNumber(orderEntity.getHouseNumber());
        orderModel.setLastname(orderEntity.getLastname());
        orderModel.setStreet(orderEntity.getStreet());
        orderModel.setZip(orderEntity.getZip());
        orderModel.setArticleList(orderEntity.getOrderList());

        OrderCreatedEvent event = new OrderCreatedEvent(orderController, orderEntity);

        // act
        orderController.createOrder(orderModel);

        // assert
        verify(eventPublisher, times(1)).publishEvent(ordercCreatedEventCaptor.capture());
        assertEquals(ordercCreatedEventCaptor.getValue().getOrder(), event.getOrder());
    }

}
