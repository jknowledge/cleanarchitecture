package de.jknowledge.cleanarchitecture.domain.aggregate.stock;

import de.jknowledge.cleanarchitecture.domain.dddbase.DomainEntity;
import de.jknowledge.cleanarchitecture.domain.dddbase.DomainEntityBase;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "article")
@Data
public class ArticleEntity extends DomainEntityBase implements DomainEntity {

    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticleEntity that = (ArticleEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
