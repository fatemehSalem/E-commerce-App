package com.micro.product.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String description;

    private double availableQuantity;

    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
