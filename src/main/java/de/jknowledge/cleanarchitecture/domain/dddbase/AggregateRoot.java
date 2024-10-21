package de.jknowledge.cleanarchitecture.domain.dddbase;

import java.util.List;

/**
 * Marker interface for aggregate roots
 */
public interface AggregateRoot extends DomainEntity {

    public List<DomainEvent> getDomainEvents();

    public List<IntegrationEvent> getIntegrationEvents();
}
