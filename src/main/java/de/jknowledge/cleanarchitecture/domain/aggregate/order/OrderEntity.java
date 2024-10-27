package de.jknowledge.cleanarchitecture.domain.aggregate.order;

import de.jknowledge.cleanarchitecture.domain.dddbase.AggregatRootBase;
import de.jknowledge.cleanarchitecture.domain.dddbase.AggregateRoot;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "orders")
@Data
public class OrderEntity extends AggregatRootBase implements AggregateRoot {

    private String firstname;

    private String lastname;

    private String zip;

    private String city;

    private String street;

    private Integer houseNumber;


    @ElementCollection
    @CollectionTable(name = "order_item_mapping",
            joinColumns = {@JoinColumn(name = "order_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "article_id")
    @Column(name = "amount")
    private Map<UUID, Integer> orderList;

    public void createOrder(String firstname, String lastname,
                            String zip, String city, String street,
                            Integer houseNumber, Map<UUID, Integer> articleMap) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.zip = zip;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
        this.orderList = articleMap;
        for(UUID articleId : articleMap.keySet()) {
            this.domainEvents.add(new ArticleOrderedEvent(this, articleId, articleMap.get(articleId)));
        }
    }

}
