package com.ecom2.order_service.product;

import java.math.BigDecimal;

public record PurchaseResponse(
        Integer productId,
        String name,
        String description,
        BigDecimal price,
        Integer quantity
) {
}
