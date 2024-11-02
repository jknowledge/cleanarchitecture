package de.jknowledge.cleanarchitecture.domain.dddbase;

import jakarta.persistence.*;

import java.util.UUID;

@MappedSuperclass
public abstract class DomainEntityBase implements IDomainEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    protected UUID id;

    @Override
    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
