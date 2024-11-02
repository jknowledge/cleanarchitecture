package de.jknowledge.cleanarchitecture.unit.domain.aggregate.order;

import de.jknowledge.cleanarchitecture.domain.aggregate.order.ArticleOrderedEvent;
import de.jknowledge.cleanarchitecture.domain.aggregate.order.OrderEntity;
import de.jknowledge.cleanarchitecture.domain.aggregate.order.OrderEntityTestDataBuilder;
import de.jknowledge.cleanarchitecture.domain.dddbase.IDomainEvent;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class OrderEntityTests {

    @Test
    public void testCreateOrder() {
        // arrange
        OrderEntity entity = new OrderEntity();
        OrderEntity expectedEntity = OrderEntityTestDataBuilder.buildMickeyMouseOrder();

        // act
        entity.createOrder(expectedEntity.getFirstname(),
                expectedEntity.getLastname(),
                expectedEntity.getZip(),
                expectedEntity.getCity(),
                expectedEntity.getStreet(),
                expectedEntity.getHouseNumber(),
                expectedEntity.getOrderList());

        // assert
        assertEquals(entity, expectedEntity);
        assertEquals(entity.getDomainEvents().size(), expectedEntity.getOrderList().size(), "expected one domain event, but is " + entity.getDomainEvents().size());
        for (IDomainEvent domainEvent : entity.getDomainEvents()) {
            assertTrue(domainEvent instanceof ArticleOrderedEvent, "domain event is not instance of " + ArticleOrderedEvent.class.getName());
        }

        for (UUID orderItemOd : expectedEntity.getOrderList().keySet()) {
            List<IDomainEvent> domainEventsForGivenOrderItem = entity.getDomainEvents().stream().filter(e -> e instanceof ArticleOrderedEvent
                    && ((ArticleOrderedEvent) e).getArticleId().equals(orderItemOd)
                    && ((ArticleOrderedEvent) e).getAmount().equals(expectedEntity.getOrderList().get(orderItemOd))).toList();
            assertFalse(domainEventsForGivenOrderItem.size() == 0, "no ArticleOrderderedEvent created for given order. Expected: 1");
            assertFalse(domainEventsForGivenOrderItem.size() > 1, "too many ArticleOrderderedEvent created for given order. Expected: 1");

        }

    }
}
