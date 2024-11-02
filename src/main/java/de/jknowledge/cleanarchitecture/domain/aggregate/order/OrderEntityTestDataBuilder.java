package de.jknowledge.cleanarchitecture.domain.aggregate.order;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class OrderEntityTestDataBuilder {

    private OrderEntityTestDataBuilder() { }

    public static OrderEntity buildMickeyMouseOrder() {
        OrderEntity orderEntity = new OrderEntity();
        Map<UUID, Integer> mickeyOrderItems = new HashMap<>();
        mickeyOrderItems.put(UUID.randomUUID(), 5);
        mickeyOrderItems.put(UUID.randomUUID(), 3);
        mickeyOrderItems.put(UUID.randomUUID(), 2);
        orderEntity.createOrder(
                "Mickey",          // Firstname
                "Mouse",           // Lastname
                "12345",           // Zip
                "Disneyland",      // City
                "Main Street",     // Street
                1,                 // House Number
                mickeyOrderItems   // Order Items
        );

        return orderEntity;
    }

    public static OrderEntity dagobertDuckOrder() {
        OrderEntity orderEntity = new OrderEntity();
        Map<UUID, Integer> dagobertOrderItems = new HashMap<>();
        dagobertOrderItems.put(UUID.randomUUID(), 10);
        dagobertOrderItems.put(UUID.randomUUID(), 4);
        dagobertOrderItems.put(UUID.randomUUID(), 7);
        orderEntity.createOrder(
                "Dagobert",         // Firstname
                "Duck",             // Lastname
                "67890",            // Zip
                "Duckburg",         // City
                "Money Bin Road",   // Street
                1,                  // House Number
                dagobertOrderItems  // Order Items
        );

        return orderEntity;
    }
}