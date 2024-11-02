package de.jknowledge.cleanarchitecture.application.controller.v1;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ArticleModel {

    private String name;

    private UUID articleID;

    private Integer stock;

}
