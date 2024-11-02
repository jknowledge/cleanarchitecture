package de.jknowledge.cleanarchitecture.domain.aggregate.stock;

import java.util.HashMap;
import java.util.UUID;

public class StockEntityTestDataBuilder {

    private StockEntityTestDataBuilder() { }

    public static StockEntity buildStockInEntenhausen() {
        StockEntity stock = new StockEntity();
        stock.setCity("Entenhausen");
        stock.setId(UUID.randomUUID());
        stock.setArticles(new HashMap<>());
        stock.getArticles().put(ArticleEntityTestDataBuilder.createAppleArticle(), 100);
        stock.getArticles().put(ArticleEntityTestDataBuilder.createBananaArticle(), 50);
        stock.getArticles().put(ArticleEntityTestDataBuilder.createPeachArticle(), 80);
        return stock;
    }
}
