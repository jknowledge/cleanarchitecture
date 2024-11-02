package de.jknowledge.cleanarchitecture.domain.aggregate.stock;

import de.jknowledge.cleanarchitecture.domain.dddbase.DomainEntityBase;
import de.jknowledge.cleanarchitecture.domain.dddbase.IDomainEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Objects;

@Entity
@Table(name = "article")
@Data
public class ArticleEntity extends DomainEntityBase implements IDomainEntity {

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
