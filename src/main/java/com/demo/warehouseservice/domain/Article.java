package com.demo.warehouseservice.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.*;

import java.io.Serializable;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Article implements Serializable {

    private static final long serialVersionUID = 561366047742513464L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private Long stock;

}
