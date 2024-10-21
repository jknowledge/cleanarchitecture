package de.jknowledge.cleanarchitecture.domain.dddbase;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public abstract class AggregatRootBase implements AggregateRoot {

    protected String id;

    protected List<IntegrationEvent> integrationEvents = new LinkedList<>();

    protected List<DomainEvent> domainEvents = new LinkedList<>();

    protected AggregatRootBase() {
        this.id = UUID.randomUUID().toString();
    }

    protected void addIntegrationEvent(IntegrationEvent event) {
        integrationEvents.add(event);
    }

    protected void addDomainEvent(DomainEvent event) {
        domainEvents.add(event);
    }

    public String getId() {
        return this.id;
    }

    public List<IntegrationEvent> getIntegrationEvents() {
        return this.integrationEvents;
    }

    public List<DomainEvent> getDomainEvents() {
        return this.domainEvents;
    }
}
