package com.ecom2.notification_service.kafka.order;

import java.math.BigDecimal;

public record Product(
        Integer productId,
        String name,
        String description,
        BigDecimal price,
        Integer quantity
) {
}
