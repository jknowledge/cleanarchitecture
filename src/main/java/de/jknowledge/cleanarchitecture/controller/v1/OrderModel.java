package de.jknowledge.cleanarchitecture.controller.v1;

import lombok.Data;

import java.util.Map;
import java.util.UUID;

@Data
public class OrderModel {

    private String firstname;

    private String lastname;

    private String zip;

    private String city;

    private String street;

    private Integer houseNumber;

    private Map<UUID, Integer> articleList;

}
