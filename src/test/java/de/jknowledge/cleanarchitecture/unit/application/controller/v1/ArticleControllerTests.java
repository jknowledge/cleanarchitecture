package de.jknowledge.cleanarchitecture.unit.application.controller.v1;

import de.jknowledge.cleanarchitecture.application.controller.v1.ArticleController;
import de.jknowledge.cleanarchitecture.application.controller.v1.ArticleModel;
import de.jknowledge.cleanarchitecture.domain.aggregate.stock.ArticleEntity;
import de.jknowledge.cleanarchitecture.domain.aggregate.stock.StockEntity;
import de.jknowledge.cleanarchitecture.domain.aggregate.stock.StockEntityTestDataBuilder;
import de.jknowledge.cleanarchitecture.infrastructure.persistence.repository.IStockRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ArticleControllerTests {

    @InjectMocks
    private ArticleController articleController;

    @Mock
    private IStockRepository stockRepository;

    @Test
    public void testList() {
        // arrange
        StockEntity stockObj = StockEntityTestDataBuilder.buildStockInEntenhausen();
        when(stockRepository.findAll()).thenReturn(List.of(stockObj));

        // act
        List<ArticleModel> articleList = articleController.list();

        // assert
        assertEquals(stockObj.getArticles().size(), articleList.size(), "mock object has " + stockObj.getArticles().size() + " articles, view model return only " + articleList.size());
        for(ArticleModel articleModel : articleList) {
            ArticleEntity articleEntity = stockObj.getArticles().keySet().stream().filter(a -> a.getId().equals(articleModel.getArticleID())).findFirst().get();
            assertNotNull(articleEntity, "view model returns article with id " + articleModel.getArticleID() + " that is not existing in mock articles");
            assertEquals(articleModel.getName(), articleEntity.getName());
            assertEquals(articleModel.getStock(), stockObj.getArticles().get(articleEntity));
        }
    }
}
