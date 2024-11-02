package de.jknowledge.cleanarchitecture.domain.dddbase;

import jakarta.persistence.*;
import org.springframework.data.domain.DomainEvents;

import java.util.LinkedList;
import java.util.List;

@MappedSuperclass
public abstract class AggregatRootBase extends DomainEntityBase implements IAggregateRoot {

    @Transient
    protected final List<IIntegrationEvent> integrationEvents = new LinkedList<>();

    @Transient
    public final List<IDomainEvent> domainEvents = new LinkedList<>();

    protected void addIntegrationEvent(IIntegrationEvent event) {
        integrationEvents.add(event);
    }

    protected void addDomainEvent(IDomainEvent event) {
        domainEvents.add(event);
    }


    public List<IIntegrationEvent> getIntegrationEvents() {
        return this.integrationEvents;
    }

    @DomainEvents
    public List<IDomainEvent> getDomainEvents() {
        return this.domainEvents;
    }
}
