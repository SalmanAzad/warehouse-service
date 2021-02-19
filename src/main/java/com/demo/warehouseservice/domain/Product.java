package com.demo.warehouseservice.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private BigDecimal price;

    @OneToMany
    private List<Article> articles;

    private Integer quantity;
}
