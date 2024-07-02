package com.micro.product.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String description;

    @OneToMany(mappedBy = "category" ,
            cascade = CascadeType.REMOVE)
    private List<Product> productList;
}
