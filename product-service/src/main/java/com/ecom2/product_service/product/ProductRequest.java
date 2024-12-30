package com.ecom2.product_service.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductRequest(
        Integer id,
        @NotNull(message = "Product name is required")
        String name,
        @NotNull(message = "Product description is required")
        String description,
        @Positive(message = "Avaliable quantity should be positive")
        int availableQuantity,
        @Positive(message = "price should be positive")
        BigDecimal price,
        @NotNull(message = "Price category is required")
        Integer categoryId
) {
}
