package de.jknowledge.cleanarchitecture.application.controller.v1;

import de.jknowledge.cleanarchitecture.domain.aggregate.stock.ArticleEntity;
import de.jknowledge.cleanarchitecture.domain.aggregate.stock.StockEntity;
import de.jknowledge.cleanarchitecture.infrastructure.persistence.repository.IStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/article/v1.0")
public class ArticleController {

    @Autowired
    private IStockRepository stockRepository;

    @GetMapping("/list")
    public List<ArticleModel> list() {
        List<ArticleModel> result = new LinkedList<>();
        List<StockEntity>  stockEntities = stockRepository.findAll();
        for(StockEntity stockObj : stockEntities) {
            for(ArticleEntity articleObj : stockObj.getArticles().keySet()) {
                result.add(ArticleModel.builder().articleID(articleObj.getId())
                        .name(articleObj.getName())
                        .stock(stockObj.getArticles().get(articleObj))
                        .build());
            }
        }
        return result;
    }
}
