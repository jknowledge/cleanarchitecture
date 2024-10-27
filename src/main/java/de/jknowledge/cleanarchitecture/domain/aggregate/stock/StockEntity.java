package de.jknowledge.cleanarchitecture.domain.aggregate.stock;

import de.jknowledge.cleanarchitecture.domain.dddbase.AggregatRootBase;
import de.jknowledge.cleanarchitecture.domain.dddbase.AggregateRoot;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.UUID;

@Entity
@Table(name="stock")
@Data
public class StockEntity extends AggregatRootBase implements AggregateRoot {

    private String city;

    @ElementCollection()
    @CollectionTable(name = "stock_article_mapping",
            joinColumns = {@JoinColumn(name = "stock_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "article_id")
    @Column(name = "stock")
    private Map<ArticleEntity, Integer> articles;

    public void decreaseStock(UUID articleId, Integer amount) throws OutOfStockException {
        if(this.articles == null) {
            throw new OutOfStockException("No articles existing.");
        }
        ArticleEntity selectedArticle = this.articles.keySet().stream().filter(a -> a.getId().equals(articleId)).findFirst().orElse(null);
        if(selectedArticle == null) {
            throw new OutOfStockException("Article with id " + articleId + " is not existing.");
        }
        if((articles.get(selectedArticle) - amount) < 0) {
            throw new OutOfStockException("Article with id " + articleId + " is not in necessary stock: " + articles.get(selectedArticle));
        }
        articles.replace(selectedArticle, articles.get(selectedArticle) - amount);
    }

}
