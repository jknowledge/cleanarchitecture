package de.jknowledge.cleanarchitecture.application.eventlistener;

import de.jknowledge.cleanarchitecture.domain.aggregate.order.ArticleOrderedEvent;
import de.jknowledge.cleanarchitecture.domain.aggregate.stock.StockEntity;
import de.jknowledge.cleanarchitecture.infrastructure.persistence.repository.IStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ArticleOrderedEventListener implements ApplicationListener<ArticleOrderedEvent> {

    @Autowired
    private IStockRepository stockRepository;

    @Override
    public void onApplicationEvent(ArticleOrderedEvent event)  {
        // TODO to make it simple we aspect to have currently only one stock
        StockEntity stockObj = stockRepository.findAll().get(0);
        stockObj.decreaseStock(event.getArticleId(), event.getAmount());
        stockRepository.save(stockObj);
    }
}
