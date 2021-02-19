package com.demo.warehouseservice.domain;

import lombok.EqualsAndHashCode;

import javax.persistence.*;

public class ProductArticle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Product product;

    @ManyToOne
    private Article article;

    private Long quantity;

}
