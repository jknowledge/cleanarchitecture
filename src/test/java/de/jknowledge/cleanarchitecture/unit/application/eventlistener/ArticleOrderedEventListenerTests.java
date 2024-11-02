package de.jknowledge.cleanarchitecture.unit.application.eventlistener;

import de.jknowledge.cleanarchitecture.application.eventlistener.ArticleOrderedEventListener;
import de.jknowledge.cleanarchitecture.domain.aggregate.order.ArticleOrderedEvent;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ExtendWith(MockitoExtension.class)
public class ArticleOrderedEventListenerTests {

    @InjectMocks
    private ArticleOrderedEventListener listener;

    @Mock
    private IStockRepository stockRepository;

    @Test
    public void testOnApplicationEvent() {
        // arrange
        StockEntity stockObj = StockEntityTestDataBuilder.buildStockInEntenhausen();
        ArticleEntity articleObj = stockObj.getArticles().keySet().iterator().next();
        int originalStock = stockObj.getArticles().get(articleObj);
        ArticleOrderedEvent event = new ArticleOrderedEvent(this, articleObj.getId(), 20);
        when(stockRepository.findAll()).thenReturn(List.of(stockObj));

        // act
        listener.onApplicationEvent(event);

        // assert
        assertEquals(stockObj.getArticles().get(articleObj), originalStock - 20, "Stock of given article is not decreased with given amount");
        verify(stockRepository, times(1)).save(stockObj);
    }
}
