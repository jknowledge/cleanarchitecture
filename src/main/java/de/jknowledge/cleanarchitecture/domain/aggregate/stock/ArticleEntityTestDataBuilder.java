package de.jknowledge.cleanarchitecture.domain.aggregate.stock;

import java.util.UUID;

public class ArticleEntityTestDataBuilder {

    private ArticleEntityTestDataBuilder() {
    }

    public static ArticleEntity createBananaArticle() {
        return createArticle("Banana");
    }

    public static ArticleEntity createPeachArticle() {
        return createArticle("Peach");
    }

    public static ArticleEntity createAppleArticle() {
        return createArticle("Apple");
    }

    private static ArticleEntity createArticle(String name) {
        ArticleEntity article = new ArticleEntity();
        article.setId(UUID.randomUUID());
        article.setName(name);
        return article;
    }
}
