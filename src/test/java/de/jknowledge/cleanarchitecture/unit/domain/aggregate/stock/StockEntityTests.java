package de.jknowledge.cleanarchitecture.unit.domain.aggregate.stock;

import de.jknowledge.cleanarchitecture.domain.aggregate.stock.ArticleEntity;
import de.jknowledge.cleanarchitecture.domain.aggregate.stock.OutOfStockException;
import de.jknowledge.cleanarchitecture.domain.aggregate.stock.StockEntity;
import de.jknowledge.cleanarchitecture.domain.aggregate.stock.StockEntityTestDataBuilder;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class StockEntityTests {

    @Test
    public void testDecreaseStockHappyPath() {
        // arrange
        StockEntity stockObj = StockEntityTestDataBuilder.buildStockInEntenhausen();
        ArticleEntity articleObj = stockObj.getArticles().keySet().iterator().next();
        Integer currentStock = stockObj.getArticles().get(articleObj);

        // act
        stockObj.decreaseStock(articleObj.getId(), 10);

        // assert
        assertEquals(stockObj.getArticles().get(articleObj), currentStock-10, "expect the decreased amount of 10 articles");
    }

    @Test
    public void testDecreaseStockNoArticlesExisting() {
        // arrange
        StockEntity stockObj = StockEntityTestDataBuilder.buildStockInEntenhausen();
        ArticleEntity articleObj = stockObj.getArticles().keySet().iterator().next();
        stockObj.setArticles(null);

        // act, assert
        OutOfStockException thrown = assertThrows(OutOfStockException.class, () -> {
            stockObj.decreaseStock(articleObj.getId(), 10);
        });
    }

    @Test
    public void testDecreaseStockInvalidArticleId() {
        // arrange
        StockEntity stockObj = StockEntityTestDataBuilder.buildStockInEntenhausen();

        // act, assert
        OutOfStockException thrown = assertThrows(OutOfStockException.class, () -> {
            stockObj.decreaseStock(UUID.randomUUID(), 10);
        });
    }

    @Test
    public void testDecreaseStockNegativeStock() {
        // arrange
        StockEntity stockObj = StockEntityTestDataBuilder.buildStockInEntenhausen();
        ArticleEntity articleObj = stockObj.getArticles().keySet().iterator().next();

        // act, assert
        OutOfStockException thrown = assertThrows(OutOfStockException.class, () -> {
            stockObj.decreaseStock(articleObj.getId(), stockObj.getArticles().get(articleObj)+1);
        });
    }
}
