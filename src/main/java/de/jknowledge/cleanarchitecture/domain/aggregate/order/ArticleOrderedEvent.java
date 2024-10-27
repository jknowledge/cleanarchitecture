package de.jknowledge.cleanarchitecture.domain.aggregate.order;

import de.jknowledge.cleanarchitecture.domain.dddbase.DomainEvent;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.UUID;

@Getter
public class ArticleOrderedEvent extends ApplicationEvent implements DomainEvent {

    private UUID articleId;

    private Integer amount;

    public ArticleOrderedEvent(Object source, UUID articleId, Integer amount) {
        super(source);
        this.articleId = articleId;
        this.amount = amount;
    }
}
