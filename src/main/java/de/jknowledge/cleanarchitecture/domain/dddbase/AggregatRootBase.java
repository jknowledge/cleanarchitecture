package de.jknowledge.cleanarchitecture.domain.dddbase;

import jakarta.persistence.*;
import org.springframework.data.domain.DomainEvents;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@MappedSuperclass
public abstract class AggregatRootBase extends DomainEntityBase implements AggregateRoot {

    @Transient
    protected List<IntegrationEvent> integrationEvents = new LinkedList<>();

    @Transient
    public List<DomainEvent> domainEvents = new LinkedList<>();

    protected void addIntegrationEvent(IntegrationEvent event) {
        integrationEvents.add(event);
    }

    protected void addDomainEvent(DomainEvent event) {
        domainEvents.add(event);
    }


    public List<IntegrationEvent> getIntegrationEvents() {
        return this.integrationEvents;
    }

    @DomainEvents
    public List<DomainEvent> getDomainEvents() {
        return this.domainEvents;
    }
}
