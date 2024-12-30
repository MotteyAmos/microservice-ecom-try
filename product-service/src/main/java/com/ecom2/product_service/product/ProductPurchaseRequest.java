package com.ecom2.product_service.product;

import jakarta.validation.constraints.NotNull;

public record ProductPurchaseRequest(
        @NotNull(message ="Product is mandatory")
        Integer productId,
        @NotNull(message = "Quantity is mandatory")
        Integer quantity
) {
}
