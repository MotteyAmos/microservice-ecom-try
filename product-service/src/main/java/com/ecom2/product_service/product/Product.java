package com.ecom2.product_service.product;


import com.ecom2.product_service.category.Category;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "productSeqGenerator")
    @SequenceGenerator(name = "productSeqGenerator", sequenceName = "product_seq", allocationSize = 1)
    private Integer id;
    private String name;
    private String description;
    private Integer availableQuantity;
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;
}
