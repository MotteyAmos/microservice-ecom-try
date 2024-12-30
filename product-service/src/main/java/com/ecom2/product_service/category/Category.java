package com.ecom2.product_service.category;


import com.ecom2.product_service.product.Product;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categorySeqGenerator")
    @SequenceGenerator(name = "categorySeqGenerator", sequenceName = "category_seq", allocationSize = 1)
    private Integer id;
    private String name;
    private String description;

    @OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE)
    private List<Product> products;
}
