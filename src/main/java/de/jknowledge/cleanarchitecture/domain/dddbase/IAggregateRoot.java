package de.jknowledge.cleanarchitecture.domain.dddbase;

import java.util.List;

/**
 * Marker interface for aggregate roots
 */
public interface IAggregateRoot extends IDomainEntity {

    List<IDomainEvent> getDomainEvents();

    List<IIntegrationEvent> getIntegrationEvents();
}
